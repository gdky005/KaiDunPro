package com.kaidun.pro.home.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Recommended {
    public String avatarUrl;
    public String date;
    public String name;
    public String content;

    public Recommended(String avatarUrl, String date, String name, String content) {
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.name = name;
        this.content = content;
    }
}
