package com.kaidun.pro.notebook.bean;

import java.io.Serializable;

/**
 * @author Yunr
 * @date 2018/01/23 13:37
 */
public class FamContact implements Serializable{

    /**
     * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
     * courseSortName : ABC
     * limit : 0
     */

    private String courseSortId;
    private String courseSortName;
    private int limit;

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
