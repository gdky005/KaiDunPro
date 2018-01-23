package com.kaidun.pro.notebook.bean;

import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/23 15:35
 */
public class FamContent {
    /**
     * message : 成功
     * result : [{"bookName":"ABC","ccId":"4B93B97398216E08E0531064410ABCF4","classId":"71801b30-e69c-467b-aab7-bf4323bbd9e8","courseSortId":"fd23aa51-dd03-4896-98cf-254864f646a9","listingRate":"100%","readingRate":"0%","speakingRate":"0%","unitCode":"88","writingRate":"0%"},{"bookName":"ABC","ccId":"4B93B97398216E08E0531064410ABCF4","classId":"71801b30-e69c-467b-aab7-bf4323bbd9e8","courseSortId":"fd23aa51-dd03-4896-98cf-254864f646a9","listingRate":"%","readingRate":"%","speakingRate":"%","unitCode":"87","writingRate":"%"}]
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private int statusCode;
    private int ver;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bookName : ABC
         * ccId : 4B93B97398216E08E0531064410ABCF4
         * classId : 71801b30-e69c-467b-aab7-bf4323bbd9e8
         * courseSortId : fd23aa51-dd03-4896-98cf-254864f646a9
         * listingRate : 100%
         * readingRate : 0%
         * speakingRate : 0%
         * unitCode : 88
         * writingRate : 0%
         */

        private String bookName;
        private String ccId;
        private String classId;
        private String courseSortId;
        private String listingRate;
        private String readingRate;
        private String speakingRate;
        private String unitCode;
        private String writingRate;

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getCcId() {
            return ccId;
        }

        public void setCcId(String ccId) {
            this.ccId = ccId;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getCourseSortId() {
            return courseSortId;
        }

        public void setCourseSortId(String courseSortId) {
            this.courseSortId = courseSortId;
        }

        public String getListingRate() {
            return listingRate;
        }

        public void setListingRate(String listingRate) {
            this.listingRate = listingRate;
        }

        public String getReadingRate() {
            return readingRate;
        }

        public void setReadingRate(String readingRate) {
            this.readingRate = readingRate;
        }

        public String getSpeakingRate() {
            return speakingRate;
        }

        public void setSpeakingRate(String speakingRate) {
            this.speakingRate = speakingRate;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getWritingRate() {
            return writingRate;
        }

        public void setWritingRate(String writingRate) {
            this.writingRate = writingRate;
        }
    }
}
