package com.cheer.forumDemo.dao;

import com.cheer.forumDemo.model.Blog;
import com.cheer.forumDemo.model.BlogVO;

import java.util.List;

public interface BLogMapper {
    /**
     * 添加博客
     * @param blog
     * @return
     */
    int insert(Blog blog);

    /**
     * 查询整条博客
     * @return
     */
    List<Blog> getList();

    int update(Blog blog);

    Blog getBlog(int id);

    List<BlogVO> getOne(String username);

    /**
     * 查询博客总数
     * @return
     */
    int number();
}
