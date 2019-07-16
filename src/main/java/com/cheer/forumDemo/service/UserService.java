package com.cheer.forumDemo.service;

import com.cheer.forumDemo.model.User;

import java.util.List;

public interface UserService {
    /**
     * 注册
     * @param user 用户名和密码就好
     * @return 成功返回一
     */
    int register(User user);

    /**
     * 用于验证登录信息
     * @param username 用户输入信息
     * @return 输入不正确返回null
     */

    User getList(String username);

    /**
     * 查询注册人数
     * @return
     */
    int number();

}
