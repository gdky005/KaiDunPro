package com.kaidun.pro.home.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Notification {
    public String date;
    public String content;
    public String contentType;

    public Notification(String date, String content, String contentType) {
        this.date = date;
        this.content = content;
        this.contentType = contentType;
    }
}
