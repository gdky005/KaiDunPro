package com.kaidun.pro.bean;

import java.util.List;

/**
 * PicBean
 * Created by WangQing on 2018/1/24.
 */

public class PicBean {

    /**
     * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
     * courseSortName : ABC
     * dvdList : [{"bookCode":"1","bookUrl":"http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png","courseSortId":"40051078-ce55-45ce-95a3-67aaca1796aa","courseSortName":"ABC"}]
     */

    private String courseSortId;
    private String courseSortName;
    private List<DvdListBean> dvdList;

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

    public List<DvdListBean> getDvdList() {
        return dvdList;
    }

    public void setDvdList(List<DvdListBean> dvdList) {
        this.dvdList = dvdList;
    }

    public static class DvdListBean {
        /**
         * bookCode : 1
         * bookUrl : http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png
         * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
         * courseSortName : ABC
         */

        private String bookCode;
        private String bookUrl;
        private String courseSortId;
        private String courseSortName;

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public String getBookUrl() {
            return bookUrl;
        }

        public void setBookUrl(String bookUrl) {
            this.bookUrl = bookUrl;
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
    }

    @Override
    public String toString() {
        return "{" +
                "courseSortId:'" + courseSortId + '\'' +
                ", courseSortName:'" + courseSortName + '\'' +
                ", dvdList:" + dvdList +
                '}';
    }
}
