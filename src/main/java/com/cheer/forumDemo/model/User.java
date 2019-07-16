package com.cheer.forumDemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String picture;
    private String time;
}
