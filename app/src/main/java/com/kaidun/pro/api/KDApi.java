package com.kaidun.pro.api;

import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.KDListBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.bean.PicBean;
import com.kaidun.pro.bean.SubVideoBean;
import com.kaidun.pro.bean.VideoBean;
import com.kaidun.pro.home.bean.CourseInfo;
import com.kaidun.pro.home.bean.Notification;
import com.kaidun.pro.home.bean.Recommended;
import com.kaidun.pro.home.bean.SchoolNotification;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.notebook.bean.FamilyContact;
import com.kaidun.pro.notebook.bean.MsgBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * ZKService retrofit2
 */

public interface KDApi {

    /**
     * 登录接口
     */
    @POST("login")
    Call<KDBaseBean<LoginBean>> login(@Body RequestBody requestBody);

    /**
     * 引导页角色查询
     */
    @POST("selectFamilyRole")
    Call<KDBaseBean<List<FamilyRoleBean>>> selectFamilyRole(@Body RequestBody requestBody);

    /**
     * 引导页角色查询
     */
    @POST("updateFamilyRole")
    Call<KDBaseBean<String>> updateFamilyRole(@Body RequestBody requestBody);

    /**
     * 家长端主页面校区通知，老师评语
     */
    @POST("selectFamilyInfo")
    Call<SchoolNotification> selectFamilyInfo(@Body RequestBody requestBody);

    /**
     * 获取所有的视频消息
     */
    @POST("selectBookCode")
    Call<KDBaseBean<List<VideoBean>>> getAllVideo(@Body RequestBody requestBody);

    /**
     * 获取二级视频
     */
    @POST("selectDvdSource")
    Call<KDBaseBean<List<SubVideoBean>>> getAllSubVideo(@Body RequestBody requestBody);

    /**
     * 查询家长拥有的家联本列表
     */
    @POST("selectFamilyContact")
    Call<FamilyContact> selectFamilyContact(@Body RequestBody requestBody);

    /**
     * 家联本内容展示（听说读写完成率）
     */
    @POST("selectFamContContext")
    Call<FamContent> selectFamContContext(@Body RequestBody requestBody);

    /**
     * 给班级老师送花
     */
    @POST("sendFolwer")
    Call<MsgBean> sendFolwer(@Body RequestBody requestBody);

    /**
     * 获取所有的图片
     */
    @POST("selectFamilyPicture")
    Call<KDListBaseBean<PicBean>> selectFamilyPicture(@Body RequestBody requestBody);

    /**
     * 推荐
     */
    @POST("recommend")
    Call<KDBaseBean> recommend(@Body RequestBody requestBody);

    /**
     * 站内信：推送通知消息
     */
    @POST("getPushMessage")
    Call<Notification> getPushMessage(@Body RequestBody requestBody);

    /**
     * 站内信：推荐历史纪录
     */
    @POST("getRecommend")
    Call<Recommended> getRecommend(@Body RequestBody requestBody);

    /**
     * 家长端主页面渲染，获取学员课程类别和对应的书本号
     */
    @POST("selectClassCourseInfo")
    Call<CourseInfo> selectClassCourseInfo(@Body RequestBody requestBody);

    /**
     * 家长主页面书本完成率
     */
    @POST("selectBookFinshRate")
    Call<KDBaseBean> selectBookFinishRate(@Body RequestBody requestBody);
}
