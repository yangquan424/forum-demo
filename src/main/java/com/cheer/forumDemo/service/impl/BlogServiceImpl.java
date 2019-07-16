package com.cheer.forumDemo.service.impl;

import com.cheer.forumDemo.dao.BLogMapper;
import com.cheer.forumDemo.model.Blog;
import com.cheer.forumDemo.model.BlogVO;
import com.cheer.forumDemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BLogMapper bLogMapper;
    @Override
    public int insert(Blog blog) {
        return this.bLogMapper.insert(blog);
    }

    @Override
    public List<Blog> getList() {
        return this.bLogMapper.getList();
    }


    @Override
    public int update(Blog blog) {
        return this.bLogMapper.update(blog);
    }

    @Override
    public Blog getBlog(int id) {
        return this.bLogMapper.getBlog(id);
    }

    @Override
    public int number() {
        return this.bLogMapper.number();
    }

    @Override
    public List<BlogVO> getOne(String username) {
        return this.bLogMapper.getOne(username);
    }
}
