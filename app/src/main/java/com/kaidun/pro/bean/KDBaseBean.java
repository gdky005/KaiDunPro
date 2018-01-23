package com.kaidun.pro.bean;

/**
 * BaseBean
 * Created by WangQing on 2018/1/23.
 */

public class KDBaseBean<T> {

    /**
     * message : 成功
     * result : {"data":{"areaCode":"1001","stuCode":"10009010","stuHeadImg":"https://headicon-1255920593.cos.ap-shanghai.myqcloud.com/43465f0d-253f-4dd8-9afc-ec1e24116203_HeadIcon.png","stuId":"43465f0d-253f-4dd8-9afc-ec1e24116203","stuName":"李悅轩/Eric","userCode":"10009010"},"reminder":"It is good that children are good.","token":"eyJhbGciOiJIUzI1NiJ9.eyJtYWNoaW5lQ29kZSI6ImFzZGFzMjM0MiIsInRpbWUiOiIxNTE2NjkwNjkwNTM0In0.sc0mFPxpt3dgCjcpghVNXUcEPvU_gpu_GN3A5pcrhVw"}
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private T result;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
