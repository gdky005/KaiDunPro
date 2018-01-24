package com.kaidun.pro.notebook.bean;

/**
 * @author Yunr
 * @date 2018/01/24 22:02
 */
public class MsgBean {


    /**
     * message : 成功
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private int statusCode;
    private int ver;

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

}
