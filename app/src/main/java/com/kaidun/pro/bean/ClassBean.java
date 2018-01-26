package com.kaidun.pro.bean;

import java.util.List;

/**
 * Created by lmj on 2018/1/26.
 */

public class ClassBean {


    /**
     * message : 成功
     * result : [{"classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1"}]
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
         */

        private String classId;
        private String className;

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
    }
}
