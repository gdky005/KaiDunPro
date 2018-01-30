package com.kaidun.pro.bean;

import java.util.List;

/**
 * Created by lmj on 2018/1/26.
 */

public class ReadAndUnReadBean {


    /**
     * message : 成功
     * result : [{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","isRemove":"Y","kfmBackout":"001","kfmCode":"1420","kfmId":"38c19066-7c1c-4b30-82b9-f9e9472e4c6e","kfmMsgText":"阿鲁YY","kfmMsgTime":"2018/01/30 13:47:01","kfmMsgTitle":"循序渐进","kfmRole":"002","kfmSender":"c8390c74-5aea-4952-aa0c-89e623c3c29a","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseId":"63E6C5E6A6A3BD38E0530C01000A8339","courseUnit":"11.Review1-2","isRemove":"N","kfmBackout":"001","kfmCode":"1409","kfmId":"8240bd27-f5f9-4cf5-ac95-6f067eb02ca5","kfmMsgText":"你们好吗？","kfmMsgTime":"2018/01/29 17:17:01","kfmMsgTitle":"同学们","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"9ce48b68-7b32-4389-90d2-5a42328a8986","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseId":"63E6C5E6A6A3BD38E0530C01000A8339","courseUnit":"11.Review1-2","isRemove":"N","kfmBackout":"001","kfmCode":"1395","kfmId":"04ad1ac2-8d94-4227-a8c3-81b329222abe","kfmMsgText":"近来可好","kfmMsgTime":"2018/01/29 16:30:10","kfmMsgTitle":"？？？","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"9ce48b68-7b32-4389-90d2-5a42328a8986","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","isRemove":"N","kfmBackout":"002","kfmCode":"1210","kfmId":"6c569aa0-886a-429f-9cd6-302db0e28217","kfmMsgText":"你有ss一asd只小asda毛驴aaa，从腊月asdasads也不骑","kfmMsgTime":"2018/01/23 15:40:38","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","isRemove":"N","kfmBackout":"002","kfmCode":"1209","kfmId":"780844cd-c70d-49f7-86da-16553ee874a0","kfmMsgText":"你有一asd只小asda毛驴aaa，从腊月asdasads也不骑","kfmMsgTime":"2018/01/23 15:39:58","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0}]
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
         * classId : c1ca6da9-670b-4b5e-8610-42289a4fb8cf
         * className : ABC1000-1
         * isRemove : Y
         * kfmBackout : 001
         * kfmCode : 1420
         * kfmId : 38c19066-7c1c-4b30-82b9-f9e9472e4c6e
         * kfmMsgText : 阿鲁YY
         * kfmMsgTime : 2018/01/30 13:47:01
         * kfmMsgTitle : 循序渐进
         * kfmRole : 002
         * kfmSender : c8390c74-5aea-4952-aa0c-89e623c3c29a
         * limit : 0
         * courseId : 63E6C5E6A6A3BD38E0530C01000A8339
         * courseUnit : 11.Review1-2
         * kfmReceiveId : c8390c74-5aea-4952-aa0c-89e623c3c29a
         */

        private String classId;
        private String className;
        private String isRemove;
        private String kfmBackout;
        private String kfmCode;
        private String kfmId;
        private String kfmMsgText;
        private String kfmMsgTime;
        private String kfmMsgTitle;
        private String kfmRole;
        private String kfmSender;
        private int limit;
        private String courseId;
        private String courseUnit;
        private String kfmReceiveId;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getIsRemove() {
            return isRemove;
        }

        public void setIsRemove(String isRemove) {
            this.isRemove = isRemove;
        }

        public String getKfmBackout() {
            return kfmBackout;
        }

        public void setKfmBackout(String kfmBackout) {
            this.kfmBackout = kfmBackout;
        }

        public String getKfmCode() {
            return kfmCode;
        }

        public void setKfmCode(String kfmCode) {
            this.kfmCode = kfmCode;
        }

        public String getKfmId() {
            return kfmId;
        }

        public void setKfmId(String kfmId) {
            this.kfmId = kfmId;
        }

        public String getKfmMsgText() {
            return kfmMsgText;
        }

        public void setKfmMsgText(String kfmMsgText) {
            this.kfmMsgText = kfmMsgText;
        }

        public String getKfmMsgTime() {
            return kfmMsgTime;
        }

        public void setKfmMsgTime(String kfmMsgTime) {
            this.kfmMsgTime = kfmMsgTime;
        }

        public String getKfmMsgTitle() {
            return kfmMsgTitle;
        }

        public void setKfmMsgTitle(String kfmMsgTitle) {
            this.kfmMsgTitle = kfmMsgTitle;
        }

        public String getKfmRole() {
            return kfmRole;
        }

        public void setKfmRole(String kfmRole) {
            this.kfmRole = kfmRole;
        }

        public String getKfmSender() {
            return kfmSender;
        }

        public void setKfmSender(String kfmSender) {
            this.kfmSender = kfmSender;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseUnit() {
            return courseUnit;
        }

        public void setCourseUnit(String courseUnit) {
            this.courseUnit = courseUnit;
        }

        public String getKfmReceiveId() {
            return kfmReceiveId;
        }

        public void setKfmReceiveId(String kfmReceiveId) {
            this.kfmReceiveId = kfmReceiveId;
        }
    }
}
