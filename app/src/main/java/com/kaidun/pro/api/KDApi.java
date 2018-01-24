package com.kaidun.pro.api;

import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.bean.VideoBean;
import com.kaidun.pro.home.bean.SchoolNotification;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * ZKService retrofit2
 */

public interface KDApi {


    /**
     * 登录接口
     */
    @POST("login")
    Call<KDBaseBean<LoginBean>> login(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);

    /**
     * 引导页角色查询
     */
    @POST("selectFamilyRole")
    Call<FamilyRoleBean> selectFamilyRole(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);

    /**
     * 家长端主页面校区通知，老师评语
     */
    @POST("selectFamilyInfo")
    Call<SchoolNotification> selectFamilyInfo(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);

    /**
     * 获取所有视频
     */
    @POST("selectBookCode")
    Call<VideoBean> getAllVideo(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);
}
