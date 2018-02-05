package com.kaidun.pro.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class CourseSchedule {
    /**
     * message : 成功
     * result : [{"bookCode":"4","courseSortName":"ABC","listingRate":"0%","readingRate":"0%","speakingRate":"0%","writingRate":"0%"}]
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
         * bookCode : 4
         * courseSortName : ABC
         * listingRate : 0%
         * readingRate : 0%
         * speakingRate : 0%
         * writingRate : 0%
         */

        private String bookCode;
        private String courseSortName;
        private String listingRate;
        private String readingRate;
        private String speakingRate;
        private String writingRate;

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public String getCourseSortName() {
            return courseSortName;
        }

        public void setCourseSortName(String courseSortName) {
            this.courseSortName = courseSortName;
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

        public String getWritingRate() {
            return writingRate;
        }

        public void setWritingRate(String writingRate) {
            this.writingRate = writingRate;
        }
    }
}
