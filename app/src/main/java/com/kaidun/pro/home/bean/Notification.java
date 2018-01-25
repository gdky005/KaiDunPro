package com.kaidun.pro.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Notification {
    /**
     * message : 成功
     * result : [{"kpmContextMessage":"凯顿儿童美语又推出新的产品了","kpmDate":"2018-01-25 00:00:00","kpmTitle":"产品推送"}]
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
         * kpmContextMessage : 凯顿儿童美语又推出新的产品了
         * kpmDate : 2018-01-25 00:00:00
         * kpmTitle : 产品推送
         */

        private String kpmContextMessage;
        private String kpmDate;
        private String kpmTitle;

        public String getKpmContextMessage() {
            return kpmContextMessage;
        }

        public void setKpmContextMessage(String kpmContextMessage) {
            this.kpmContextMessage = kpmContextMessage;
        }

        public String getKpmDate() {
            return kpmDate;
        }

        public void setKpmDate(String kpmDate) {
            this.kpmDate = kpmDate;
        }

        public String getKpmTitle() {
            return kpmTitle;
        }

        public void setKpmTitle(String kpmTitle) {
            this.kpmTitle = kpmTitle;
        }
    }
}
