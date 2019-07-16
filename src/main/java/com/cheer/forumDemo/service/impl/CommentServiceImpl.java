package com.cheer.forumDemo.service.impl;

import com.cheer.forumDemo.dao.CommentMapper;
import com.cheer.forumDemo.model.Comment;
import com.cheer.forumDemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public int insert(Comment comment) {
        return this.commentMapper.insert(comment);
    }

    @Override
    public List<Comment> getComment(String title,int id) {
        return this.commentMapper.getComment(title,id);
    }

    @Override
    public int count(int id) {
        return commentMapper.count(id);
    }

    @Override
    public int number() {
        return this.commentMapper.number();
    }

    @Override
    public String getUser(int id) {
        return this.commentMapper.getUser(id);
    }
}
