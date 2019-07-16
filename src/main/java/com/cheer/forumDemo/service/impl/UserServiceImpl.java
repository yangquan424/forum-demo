package com.cheer.forumDemo.service.impl;


import com.cheer.forumDemo.dao.UserMapper;
import com.cheer.forumDemo.model.User;
import com.cheer.forumDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int register(User user) {
        return this.userMapper.register(user);
    }

    @Override
    public User getList(String username) {
        return this.userMapper.getList(username);
    }

    @Override
    public int number() {
        return this.userMapper.number();
    }

}
