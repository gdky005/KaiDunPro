package com.kaidun.pro;

import android.content.Context;
import android.content.Intent;

import com.kaidun.pro.activity.PhotoViewActivity;
import com.kaidun.pro.activity.VideoPlayActivity;

/**
 * PageCtrl
 * Created by WangQing on 2018/1/26.
 */

public class PageCtrl {

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

}
