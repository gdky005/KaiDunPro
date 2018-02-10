package com.kaidun.pro.bean;

/**
 * Created by Doraemon on 2018/2/9.
 */

public class AccountData {
    String userCode;
    String pwd;

    public AccountData(String userCode, String pwd) {
        this.userCode = userCode;
        this.pwd = pwd;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return userCode;
    }
}
