package com.kaidun.pro.bean;

/**
 * Created by lmj on 2018/1/23.
 */

public class SwipeBean {

    public String avatarUrl;
    public String date;
    public String name;
    public String content;
    public String replyContent;
    public int type;

    public SwipeBean(String avatarUrl, String date, String name, String content,String reply) {
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.name = name;
        this.content = content;
        this.replyContent = reply;
    }

    public SwipeBean(String avatarUrl, String date, String name, String content, String replyContent, int type) {
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.name = name;
        this.content = content;
        this.replyContent = replyContent;
        this.type = type;
    }
}
