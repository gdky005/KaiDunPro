package com.kaidun.pro.bean;

/**
 * author chmyy
 * created on 2018/1/25
 * email fat_chao@163.com.
 */

public class SubVideoBean {

    /**
     * tageTitle : sour
     * thumbnallUrl : http://211.152.60.252:8088/course/ABC/FB2/flashcards/sour.png
     */

    private String tageTitle;
    private String thumbnallUrl;
    private int type;


    public String getDvdUrl() {
        return dvdUrl;
    }

    public void setDvdUrl(String dvdUrl) {
        this.dvdUrl = dvdUrl;
    }

    private String dvdUrl;

    public String getTageTitle() {
        return tageTitle;
    }

    public void setTageTitle(String tageTitle) {
        this.tageTitle = tageTitle;
    }

    public String getThumbnallUrl() {
        return thumbnallUrl;
    }

    public void setThumbnallUrl(String thumbnallUrl) {
        this.thumbnallUrl = thumbnallUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
