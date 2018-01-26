package com.kaidun.pro.bean;

import java.util.List;

/**
 * Created by lmj on 2018/1/26.
 */

public class MsgDetailBean {


    /**
     * message : 成功
     * result : [{"isRemove":"Y","kmdCode":"10000056","kmdId":"f0634fe0-0092-4593-94f9-a8a874781443","kmdMsgText":"****************","kmdMsgTime":"2018/01/24 15:40:44","kmdRole":"妈妈"},{"isRemove":"Y","kmdCode":"10000057","kmdId":"dd1f403c-f437-4bde-9f02-dbc030a14301","kmdMsgText":"hello","kmdMsgTime":"2018/01/25 16:51:54","kmdRole":"妈妈"}]
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
         * isRemove : Y
         * kmdCode : 10000056
         * kmdId : f0634fe0-0092-4593-94f9-a8a874781443
         * kmdMsgText : ****************
         * kmdMsgTime : 2018/01/24 15:40:44
         * kmdRole : 妈妈
         */

        private String isRemove;
        private String kmdCode;
        private String kmdId;
        private String kmdMsgText;
        private String kmdMsgTime;
        private String kmdRole;

        public String getIsRemove() {
            return isRemove;
        }

        public void setIsRemove(String isRemove) {
            this.isRemove = isRemove;
        }

        public String getKmdCode() {
            return kmdCode;
        }

        public void setKmdCode(String kmdCode) {
            this.kmdCode = kmdCode;
        }

        public String getKmdId() {
            return kmdId;
        }

        public void setKmdId(String kmdId) {
            this.kmdId = kmdId;
        }

        public String getKmdMsgText() {
            return kmdMsgText;
        }

        public void setKmdMsgText(String kmdMsgText) {
            this.kmdMsgText = kmdMsgText;
        }

        public String getKmdMsgTime() {
            return kmdMsgTime;
        }

        public void setKmdMsgTime(String kmdMsgTime) {
            this.kmdMsgTime = kmdMsgTime;
        }

        public String getKmdRole() {
            return kmdRole;
        }

        public void setKmdRole(String kmdRole) {
            this.kmdRole = kmdRole;
        }
    }
}
