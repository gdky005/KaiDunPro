package com.kaidun.pro.notebook.bean;

import java.io.Serializable;

/**
 * @author Yunr
 * @date 2018/01/23 13:37
 */
public class FamContact implements Serializable {

    /**
     * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
     * courseSortName : ABC
     * csUrl：图片地址
     * limit : 0
     */

    private String courseSortId;
    private String courseSortName;
    private String csUrl;
    private int limit;

    /**
     * 敬请期待选项
     */
    public FamContact() {
        this.courseSortName = "敬请期待...";
    }

    public String getCsUrl() {
        return csUrl;
    }

    public void setCsUrl(String csUrl) {
        this.csUrl = csUrl;
    }

    public String getCourseSortId() {
        return courseSortId;
    }

    public void setCourseSortId(String courseSortId) {
        this.courseSortId = courseSortId;
    }

    public String getCourseSortName() {
        return courseSortName;
    }

    public void setCourseSortName(String courseSortName) {
        this.courseSortName = courseSortName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
