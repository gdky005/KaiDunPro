package com.kaidun.pro;

import android.content.Context;
import android.content.Intent;

import com.kaidun.pro.activity.LoginActivity;
import com.kaidun.pro.activity.PhotoViewActivity;
import com.kaidun.pro.activity.VideoPlayActivity;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.managers.KDAccountManager;

/**
 * PageCtrl
 * Created by WangQing on 2018/1/26.
 */

public class PageCtrl {

    /**
     * 启动 登录页面
     *
     * @param context context
     */
    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 启动 图片
     *
     * @param context context
     * @param picPath picPath
     */
    public static void startPhotoView(Context context, String picPath) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.FLAG_PIC_PATH_KEY, picPath);
        context.startActivity(intent);
    }

    /**
     * 启动视频播放页面
     *
     * @param context   context
     * @param videoName 视频名字
     * @param thumbUrl  视频缩略图
     * @param videoUrl  视频播放 Url
     */
    public static void startVideoPlay(Context context, String videoName, String thumbUrl, String videoUrl) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(VideoPlayActivity.FLAG_VIDEO_NAME, videoName);
        intent.putExtra(VideoPlayActivity.FLAG_VIDEO_THUMB_URL, thumbUrl);
        intent.putExtra(VideoPlayActivity.FLAG_VIDEO_URL, videoUrl);
        context.startActivity(intent);
    }


    /**
     * 从推送进入到主界面里面
     *
     * @param context     context
     * @param isUnReadMsg 是否为 未读消息主页面？ 如果不是进入 图片主页面
     */
    public static void startMainActivityForPush(Context context, boolean isUnReadMsg) {
        LoginBean.DataBean userInfoBean = KDAccountManager.getInstance().getUserInfoBean();

        if (userInfoBean == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(MainActivity.FLAG_PUSH_KEY, isUnReadMsg ? MainActivity.NAV_TYPE_MESSAGE : MainActivity.NAV_TYPE_PICTURE);
            context.startActivity(intent);
        }
    }
}
