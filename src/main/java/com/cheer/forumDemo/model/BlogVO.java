package com.cheer.forumDemo.model;


import lombok.Data;

@Data
public class BlogVO extends Blog {
    private String lastTime;
    private String username;
}
