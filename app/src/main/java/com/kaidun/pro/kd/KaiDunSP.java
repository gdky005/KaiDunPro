package com.kaidun.pro.kd;

import team.zhuoke.sdk.exception.ZKSharePreferencesException;
import team.zhuoke.sdk.sharedpreferences.ZKSharedPreferences;

/**
 * Created by WangQing on 2018/1/25.
 */

public class KaiDunSP extends ZKSharedPreferences {
    private final String SP_NAME = "kd_first";
    public static final String KEY_TEST_WELCOME = "KEY_TEST_WELCOME";
    public static final String KEY_TEST_ROLES = "KEY_TEST_ROLES";
    public static final String KEY_USERCODE_AND_PWD = "KEY_USERCODE_AND_PWD";
//    public static final String KEY_USERPWD = "KEY_USERPWD";

    public String sharedPreferencesFileName() {
        return SP_NAME;
    }

    @Override
    public void put(String key, Object object) {
        try {
            super.put(key, object);
        } catch (ZKSharePreferencesException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key, Object defaultObject) {
        try {
            return super.get(key, defaultObject);
        } catch (ZKSharePreferencesException e) {
            e.printStackTrace();
        }
        return null;
    }
}
