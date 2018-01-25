package com.kaidun.pro.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.PhoneUtils;
import com.kaidun.pro.Constant;
import com.kaidun.pro.managers.KDAccountManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * KDRequestUtils
 * Created by WangQing on 2018/1/23.
 */
public class KDRequestUtils {

    /**
     * 获取通用的请求 Header, 已经统一在 Okhttp 的拦截器里面处理，不需要手动调用
     *
     * @return headerMaps
     */
    public static Map<String, String> getHeaderMaps() {
        Map<String, String> headerMaps = new HashMap<>();


        String deviceId = null;
        try {
            deviceId = PhoneUtils.getIMEI();
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = PhoneUtils.getIMSI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "asdas2342";
        }


        String token = KDAccountManager.getInstance().getToken();

        if (!TextUtils.isEmpty(token)) {
            headerMaps.put("Authorization", token);
        }

        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "asdas2342";
        }
        headerMaps.put("machineCode", deviceId);
        headerMaps.put("Content-Type", Constant.MEDIA_TYPE);

        return headerMaps;
    }

    /**
     * 默认包含 userCode 和 areaCode 字段。 不需要额外的数据。
     * @return RequestBody
     */
    public static RequestBody getRequestBody() {
        return KDRequestUtils.getRequestBody(new JSONObject());
    }

    /**
     * 默认包含 userCode 和 areaCode 字段。  需要添加 额外 JSONObject 数据 参数。
     * @return RequestBody
     */
    public static RequestBody getRequestBody(JSONObject jsonObject) {
        return getRequestBody(jsonObject, false);
    }

    public static RequestBody getLoginRequestBody(JSONObject jsonObject) {
        return getRequestBody(jsonObject, true);
    }

    /**
     * 仅供内部调用。
     * @param jsonObject 需要传入的  jsonObject 参数，所有的
     * @param isLogin 如果登录 true, 不会自动添加任何东西, 否则添加 userCode 和 areaCode 字段
     * @return RequestBody
     */
    private static RequestBody getRequestBody(JSONObject jsonObject, boolean isLogin) {
        if (!isLogin) {
            setCommonJsonObject(jsonObject);
        }
        return RequestBody.create(MediaType.parse(Constant.CHARSET_NAME), jsonObject.toString());
    }

    public static void setCommonJsonObject(JSONObject jsonObject) {
        try {
            KDAccountManager kdAccountManager = KDAccountManager.getInstance();

            jsonObject.put("userCode", kdAccountManager.getUserCode());
            jsonObject.put("areaCode", kdAccountManager.getAreaCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
