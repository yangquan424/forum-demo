package com.cheer.forumDemo.web.controller;


import com.cheer.forumDemo.model.Blog;
import com.cheer.forumDemo.model.BlogVO;
import com.cheer.forumDemo.model.Comment;
import com.cheer.forumDemo.model.User;
import com.cheer.forumDemo.service.BlogService;
import com.cheer.forumDemo.service.CommentService;
import com.cheer.forumDemo.service.UserService;
import com.cheer.forumDemo.utils.DateUtil;
import com.cheer.forumDemo.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Controller
public class ForumController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("blogs",this.blogService.getList());
        model.addAttribute("user",this.userService.number());
        model.addAttribute("blog",this.blogService.number());
        model.addAttribute("comment",this.commentService.number());
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String pass,
                           @RequestParam(value = "file") MultipartFile file) throws IOException {
        //设置保存文件的位置
        String basePath = "src/main/resources/static/image/";
        String fileName = file.getOriginalFilename();//获取上传文件名称
        //获取文件格式
        String fileType = fileName.substring(fileName.indexOf(".") + 1);
        String name = username;
        String path = basePath+name+"."+fileType;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));//写入到头像文件夹中
        User user = new User();
        user.setUsername(username);
        user.setPassword(StringUtil.encrypt(pass));
        user.setPicture("/image/"+name+"."+fileType);
        int row = this.userService.register(user);
        if (row == 1){
            return "error/404";
        }
        else{
            return "error/error";
        }
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable int id,Model model,HttpSession session){
        //查询博客信息
        Blog blog = this.blogService.getBlog(id);
        //放入模型
        model.addAttribute("blog",blog);
        String title = blog.getTitle();
        //通过标题查询博客评论信息,放入模型
        List<Comment> comments = this.commentService.getComment(title,id);
        model.addAttribute("comment",comments);

        return "blog";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String user, @RequestParam(value = "password") String pass, HttpSession session){
        User user1 = this.userService.getList(user);
        String password = StringUtil.encrypt(pass);
        if(user1 != null){
            if (user.equals(user1.getUsername())&&password.equals(user1.getPassword())){
                session.setAttribute("username",user);
                session.setAttribute("picture",user1.getPicture());
                return "redirect:/index";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/comment/{id}")
    public String comment(HttpSession session,@RequestParam(name = "text") String text,@PathVariable int id){
        //查询出博客标题和id
        String title = this.blogService.getBlog(id).getTitle();
        //通过标题查询该条有多少评论
        int num = this.commentService.count(id);
        //每天评论所在楼层
        num+=1;
        String name = (String) session.getAttribute("username");
        String picture = this.userService.getList(name).getPicture();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setTid(num);
        comment.setUsername(name);
        comment.setContent(text);
        comment.setTitle(title);
        comment.setPicture(picture);
        int row = this.commentService.insert(comment);
        if (row == 1){
            return "redirect:/blog/"+id;
        }else {
            return "error/error";
        }
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @PostMapping("/write")
    public String write(@RequestParam(name = "title") String title,@RequestParam(name = "content") String content,
                        HttpSession session){
        String username = (String) session.getAttribute("username");
        String picture = (String) session.getAttribute("picture");
        int row = 0;
        if(username != null||!username.equals("")){
            Blog blog = new Blog(username,title,content,picture);
            row = this.blogService.insert(blog);
        }
        if(row == 1){
            return "redirect:/index";
        }else{
            return "error/error";
        }
    }

    @RequestMapping(value = "a",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> a(String username){
        Map<String,String> messgie = new HashMap<>();
        User user = this.userService.getList(username);
        if(user!=null){
            messgie.put("code","-1");
            messgie.put("message","用户名已经存在");
        }
        return messgie;
    }

    @GetMapping("/personal/{username}")
    public String personal(@PathVariable String username,Model model,HttpSession session) throws ParseException {
        model.addAttribute("user",this.userService.getList(username));
        List<BlogVO> blogs = this.blogService.getOne(username);
        for (int i = 0; i < blogs.size(); i++) {
            blogs.get(i).setUsername(this.commentService.getUser(blogs.get(i).getId()));
            String time = blogs.get(i).getTime();
            blogs.get(i).setLastTime(DateUtil.change(time));
        }
        model.addAttribute("blogs",blogs);
        if(session != null&&session.getAttribute("username").equals(username)){
            model.addAttribute("delete","删除该主题");
        }
        return "personal";
    }
}
