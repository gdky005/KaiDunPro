package com.kaidun.pro.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class CourseInfo {
    /**
     * message : 成功
     * result : {"classCourseInfo":[{"bookModels":[{"bookCode":"1","bookUrl":"http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png"}],"className":"ABC1000-1","courseSortId":"40051078-ce55-45ce-95a3-67aaca1796aa","rateList":[{"bookCode":"1","courseSortName":"ABC","listingRate":"0%","readingRate":"0%","speakingRate":"0%","writingRate":"0%"}]}]}
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private ResultBean result;
    private int statusCode;
    private int ver;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        private List<ClassCourseInfoBean> classCourseInfo;

        public List<ClassCourseInfoBean> getClassCourseInfo() {
            return classCourseInfo;
        }

        public void setClassCourseInfo(List<ClassCourseInfoBean> classCourseInfo) {
            this.classCourseInfo = classCourseInfo;
        }

        public static class ClassCourseInfoBean extends Home {
            /**
             * bookModels : [{"bookCode":"1","bookUrl":"http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png"}]
             * className : ABC1000-1
             * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
             * rateList : [{"bookCode":"1","courseSortName":"ABC","listingRate":"0%","readingRate":"0%","speakingRate":"0%","writingRate":"0%"}]
             */

            private String className;
            private String courseSortId;
            private List<BookModelsBean> bookModels;
            private List<RateListBean> rateList;

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String getCourseSortId() {
                return courseSortId;
            }

            public void setCourseSortId(String courseSortId) {
                this.courseSortId = courseSortId;
            }

            public List<BookModelsBean> getBookModels() {
                return bookModels;
            }

            public void setBookModels(List<BookModelsBean> bookModels) {
                this.bookModels = bookModels;
            }

            public List<RateListBean> getRateList() {
                return rateList;
            }

            public void setRateList(List<RateListBean> rateList) {
                this.rateList = rateList;
            }

            public static class BookModelsBean {
                /**
                 * bookCode : 1
                 * bookUrl : http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png
                 */

                private String bookCode;
                private String bookUrl;

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
            }

            public static class RateListBean {
                /**
                 * bookCode : 1
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
    }
}
