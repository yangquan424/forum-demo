package com.cheer.forumDemo.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class StringUtil {
    // 使用md5加密
    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }
}
