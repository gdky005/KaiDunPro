package com.kaidun.pro.notebook.bean;

import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/27 21:48
 */
public class BookDetail {


    /**
     * message : 成功
     * result : {"courseObject":"然小朋友烤饼干","courseObjectList":[{"courseSentencePattern":"what can you do?i can dance","courseVocabulary":"dance","courserPhonice":"dance"},{"courseSentencePattern":"waht can you do?ican paint","courseVocabulary":"paint","courserPhonice":"paint"},{"courseSentencePattern":"what can you do?i can read","courseVocabulary":"read","courserPhonice":"read"},{"courseSentencePattern":"what can you do?i can sing","courseVocabulary":"sing","courserPhonice":"sing"}]}
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
         * courseObject : 然小朋友烤饼干
         * courseObjectList : [{"courseSentencePattern":"what can you do?i can dance","courseVocabulary":"dance","courserPhonice":"dance"},{"courseSentencePattern":"waht can you do?ican paint","courseVocabulary":"paint","courserPhonice":"paint"},{"courseSentencePattern":"what can you do?i can read","courseVocabulary":"read","courserPhonice":"read"},{"courseSentencePattern":"what can you do?i can sing","courseVocabulary":"sing","courserPhonice":"sing"}]
         */

        private String courseObject;
        private List<CourseObjectListBean> courseObjectList;

        public String getCourseObject() {
            return courseObject;
        }

        public void setCourseObject(String courseObject) {
            this.courseObject = courseObject;
        }

        public List<CourseObjectListBean> getCourseObjectList() {
            return courseObjectList;
        }

        public void setCourseObjectList(List<CourseObjectListBean> courseObjectList) {
            this.courseObjectList = courseObjectList;
        }

        public static class CourseObjectListBean {
            /**
             * courseSentencePattern : what can you do?i can dance
             * courseVocabulary : dance
             * courserPhonice : dance
             */

            private String courseSentencePattern;
            private String courseVocabulary;
            private String courserPhonice;

            public String getCourseSentencePattern() {
                return courseSentencePattern;
            }

            public void setCourseSentencePattern(String courseSentencePattern) {
                this.courseSentencePattern = courseSentencePattern;
            }

            public String getCourseVocabulary() {
                return courseVocabulary;
            }

            public void setCourseVocabulary(String courseVocabulary) {
                this.courseVocabulary = courseVocabulary;
            }

            public String getCourserPhonice() {
                return courserPhonice;
            }

            public void setCourserPhonice(String courserPhonice) {
                this.courserPhonice = courserPhonice;
            }
        }
    }
}
