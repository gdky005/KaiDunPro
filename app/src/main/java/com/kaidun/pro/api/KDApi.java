package com.kaidun.pro.api;

import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;

import java.util.List;
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
    Call<KDBaseBean<List<FamilyRoleBean>>> selectFamilyRole(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);
    /**
     * 引导页角色查询
     */
    @POST("updateFamilyRole")
    Call<KDBaseBean<String>> updateFamilyRole(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);

    /**
     * 引导页角色查询
     */
    @POST("selectFamilyInfo")
    Call<Object> selectFamilyInfo(@HeaderMap Map<String, String> headers, @Body RequestBody requestBody);
}
