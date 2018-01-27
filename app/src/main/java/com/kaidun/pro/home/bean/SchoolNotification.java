package com.kaidun.pro.home.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class SchoolNotification extends Home {

    /**
     * message : 成功
     * result : {"inform":{"releaseContent":"下周考试，请考生做好准备","releaseTime":"2018-01-25","releaser":"Crystal"},"comment":{"comment":"If you wish to proceed to the MA,be sure to inform the university of your choice","publishTime":"2018-01-29","teacher":"Cassie"}}
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
         * inform : {"releaseContent":"下周考试，请考生做好准备","releaseTime":"2018-01-25","releaser":"Crystal"}
         * comment : {"comment":"If you wish to proceed to the MA,be sure to inform the university of your choice","publishTime":"2018-01-29","teacher":"Cassie"}
         */

        private InformBean inform;
        private CommentBean comment;

        public InformBean getInform() {
            return inform;
        }

        public void setInform(InformBean inform) {
            this.inform = inform;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class InformBean {
            /**
             * releaseContent : 下周考试，请考生做好准备
             * releaseTime : 2018-01-25
             * releaser : Crystal
             */

            private String releaseContent;
            private String releaseTime;
            private String releaser;

            public String getReleaseContent() {
                return releaseContent;
            }

            public void setReleaseContent(String releaseContent) {
                this.releaseContent = releaseContent;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getReleaser() {
                return releaser;
            }

            public void setReleaser(String releaser) {
                this.releaser = releaser;
            }
        }

        public static class CommentBean {
            /**
             * comment : If you wish to proceed to the MA,be sure to inform the university of your choice
             * publishTime : 2018-01-29
             * teacher : Cassie
             */

            private String comment;
            private String publishTime;
            private String teacher;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }
        }
    }
}
