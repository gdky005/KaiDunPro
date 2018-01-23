package com.kaidun.pro.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.PhoneUtils;
import com.kaidun.pro.Constant;

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
     * 获取通用的请求 Header
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

        headerMaps.put("machineCode", deviceId);
        headerMaps.put("Content-Type", Constant.MEDIA_TYPE);

        return headerMaps;
    }

    public static RequestBody getRequestBody(JSONObject jsonObject) {
        return RequestBody.create(MediaType.parse(Constant.CHARSET_NAME), jsonObject.toString());
    }

}
