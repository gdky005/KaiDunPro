package com.kaidun.pro.home.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class SchoolNotification extends Home {
    /**
     * message : 成功
     * result : {"releaseContent":"下周考试，请考生做好准备","releaseTime":"2018/01/24\n\t\t00:00:00","releaser":"Stella"}
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
         * releaseContent : 下周考试，请考生做好准备
         * releaseTime : 2018/01/24
         * 00:00:00
         * releaser : Stella
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
}
