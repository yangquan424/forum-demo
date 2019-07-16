package com.cheer.forumDemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    private int id;
    private String username;//登录用户
    private int tid;
    private String title;
    private String content;
    private String time;
    private String picture;
}
