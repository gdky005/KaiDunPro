package com.kaidun.pro.notebook.bean;

import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/27 21:48
 */
public class BookDetail {


    /**
     * message : 成功
     * result : {"courseObject":"小朋友烤饼干","courseObjectList":{"sentenceList":["WHAT   ARE YOU DOING","WHAT   ARE YOU DOING NOW"],"phoniceList":["DASFASD","FADFAD"],"vocabularyList":["PATHOY","JAVA","C++","C"]}}
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
        /**
         * courseObject : 小朋友烤饼干
         * courseObjectList : {"sentenceList":["WHAT   ARE YOU DOING","WHAT   ARE YOU DOING NOW"],"phoniceList":["DASFASD","FADFAD"],"vocabularyList":["PATHOY","JAVA","C++","C"]}
         */

        private String courseObject;
        private CourseObjectListBean courseObjectList;

        public String getCourseObject() {
            return courseObject;
        }

        public void setCourseObject(String courseObject) {
            this.courseObject = courseObject;
        }

        public CourseObjectListBean getCourseObjectList() {
            return courseObjectList;
        }

        public void setCourseObjectList(CourseObjectListBean courseObjectList) {
            this.courseObjectList = courseObjectList;
        }

        public static class CourseObjectListBean {
            private List<String> sentenceList;
            private List<String> phoniceList;
            private List<String> vocabularyList;

            public List<String> getSentenceList() {
                return sentenceList;
            }

            public void setSentenceList(List<String> sentenceList) {
                this.sentenceList = sentenceList;
            }

            public List<String> getPhoniceList() {
                return phoniceList;
            }

            public void setPhoniceList(List<String> phoniceList) {
                this.phoniceList = phoniceList;
            }

            public List<String> getVocabularyList() {
                return vocabularyList;
            }

            public void setVocabularyList(List<String> vocabularyList) {
                this.vocabularyList = vocabularyList;
            }
        }
    }
}
