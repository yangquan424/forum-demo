package com.cheer.forumDemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String change(String time) throws ParseException {
        Date date = new Date();
        SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = ss.parse(time);
        long s1 = date.getTime()/1000;
        long s2 = date1.getTime()/1000;
        int seconds = (int) (s1 - s2);
        int day=0,hours=0,minutes=0;
        day = seconds/(60*60*24);//天
        seconds -= day*60*60*24;
        hours = seconds/(60*60);//时
        seconds -= hours*60*60;
        minutes= seconds/60;//分
        seconds -= minutes*60;//秒
        if(day == 0&& hours ==0&& minutes==0){
            return seconds+"秒";
        }else if(day == 0&&hours ==0){
            return minutes+"分钟";
        }else if(day == 0){
            return hours+"小时";
        }else{
            return day+"天";
        }
    }
}
