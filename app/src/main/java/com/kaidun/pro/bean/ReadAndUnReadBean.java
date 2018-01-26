package com.kaidun.pro.bean;

import java.util.List;

/**
 * Created by lmj on 2018/1/26.
 */

public class ReadAndUnReadBean {


    /**
     * message : 成功
     * result : [{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","kfmBackout":"001","kfmCode":"1209","kfmId":"780844cd-c70d-49f7-86da-16553ee874a0","kfmMsgText":"你有一asd只小asda毛驴aaa，从腊月asdasads也不骑","kfmMsgTime":"2018/01/23 15:39:58","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","kfmBackout":"001","kfmCode":"1208","kfmId":"2a1d77df-7a07-4601-813c-b57420bf8b8c","kfmMsgText":"你有一asd只小asda毛驴aaa，从腊月asdas也不骑","kfmMsgTime":"2018/01/23 15:39:56","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","kfmBackout":"001","kfmCode":"1207","kfmId":"6ee254a7-fd3d-4ef2-9476-042dfafa94e2","kfmMsgText":"你有一asd只小asda毛驴aaa，从腊月也不骑","kfmMsgTime":"2018/01/23 15:39:53","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","kfmBackout":"001","kfmCode":"1206","kfmId":"172d81ba-0dec-4dd5-8ec3-ec96d63b8643","kfmMsgText":"你有一asd只小毛驴aaa，从腊月也不骑","kfmMsgTime":"2018/01/23 15:39:51","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0},{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseUnit":"6.UNIT5","kfmBackout":"001","kfmCode":"1205","kfmId":"47bf4fd9-bcd0-466e-9ec7-de553092fdbd","kfmMsgText":"你有一asd只小毛驴，从腊月也不骑","kfmMsgTime":"2018/01/23 15:39:48","kfmMsgTitle":"测试asaaaaadaa数aaaaaasaaaadasd据","kfmReceiveId":"c8390c74-5aea-4952-aa0c-89e623c3c29a","kfmRole":"","kfmSender":"230d99d4-8c50-4df0-b827-a1c6ad654ad7","limit":0}]
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
         * courseUnit : 6.UNIT5
         * kfmBackout : 001
         * kfmCode : 1209
         * kfmId : 780844cd-c70d-49f7-86da-16553ee874a0
         * kfmMsgText : 你有一asd只小asda毛驴aaa，从腊月asdasads也不骑
         * kfmMsgTime : 2018/01/23 15:39:58
         * kfmMsgTitle : 测试asaaaaadaa数aaaaaasaaaadasd据
         * kfmReceiveId : c8390c74-5aea-4952-aa0c-89e623c3c29a
         * kfmRole :
         * kfmSender : 230d99d4-8c50-4df0-b827-a1c6ad654ad7
         * limit : 0
         */

        private String classId;
        private String className;
        private String courseUnit;
        private String kfmBackout;
        private String kfmCode;
        private String kfmId;
        private String kfmMsgText;
        private String kfmMsgTime;
        private String kfmMsgTitle;
        private String kfmReceiveId;
        private String kfmRole;
        private String kfmSender;
        private int limit;

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

        public String getCourseUnit() {
            return courseUnit;
        }

        public void setCourseUnit(String courseUnit) {
            this.courseUnit = courseUnit;
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

        public String getKfmReceiveId() {
            return kfmReceiveId;
        }

        public void setKfmReceiveId(String kfmReceiveId) {
            this.kfmReceiveId = kfmReceiveId;
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
    }
}
