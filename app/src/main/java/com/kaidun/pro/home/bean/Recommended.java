package com.kaidun.pro.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Recommended {

    /**
     * message : 成功
     * result : [{"cellPhoneNumber":"17682339999","childName":"测试他儿","familyName":"测试"},{"cellPhoneNumber":"15102121268","childName":"******","familyName":"*****"},{"cellPhoneNumber":"15102121264","childName":"******","familyName":"*****"},{"cellPhoneNumber":"15102121265","childName":"欧阳","familyName":"欧阳"}]
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
         * cellPhoneNumber : 17682339999
         * childName : 测试他儿
         * familyName : 测试
         */

        private String cellPhoneNumber;
        private String childName;
        private String familyName;

        public String getCellPhoneNumber() {
            return cellPhoneNumber;
        }

        public void setCellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
        }

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }
    }
}
