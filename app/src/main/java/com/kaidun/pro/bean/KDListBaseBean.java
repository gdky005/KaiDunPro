package com.kaidun.pro.bean;

import java.util.List;

/**
 * KDListBaseBean
 * Created by WangQing on 2018/1/25.
 */

public class KDListBaseBean<T> {

    /**
     * message : 成功
     * result : [{"courseSortId":"40051078-ce55-45ce-95a3-67aaca1796aa","courseSortName":"ABC","dvdList":[{"bookCode":"1","bookUrl":"http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png","courseSortId":"40051078-ce55-45ce-95a3-67aaca1796aa","courseSortName":"ABC"}]}]
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private int statusCode;
    private int ver;
    private List<T> result;

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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "message:'" + message + '\'' +
                ", statusCode:" + statusCode +
                ", ver:" + ver +
                ", result:" + result +
                '}';
    }
}
