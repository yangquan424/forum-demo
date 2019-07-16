package com.cheer.forumDemo.dao;

import com.cheer.forumDemo.model.Comment;

import java.util.List;

public interface CommentMapper {

    /**
     * 记录会员评论信息
     * @param comment
     * @return
     */
    int insert(Comment comment);

    /**
     * 查询每条帖子
     * @return
     */
    List<Comment> getComment(String title,int id);

    /**
     * 每条博客评论次数
     * @param id
     * @return
     */
    int count(int id);

    /**
     * 查询博客总回复数量
     * @return
     */
    int number();

    /**
     * 某人某条主题最后评论的人
     * @param id
     * @return
     */
    String getUser(int id);
}
