package com.cheer.forumDemo.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Blog implements Serializable {
    private int id;
    private String user;
    private String title;
    private String text;
    private String time;
    private String picture;

    public Blog() {
    }

    public Blog(String username, String title, String content, String picture) {
        this.user = username;
        this.title = title;
        this.text = content;
        this.picture = picture;
    }
}
