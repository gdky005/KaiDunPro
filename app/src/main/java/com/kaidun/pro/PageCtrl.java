package com.kaidun.pro;

import android.content.Context;
import android.content.Intent;

import com.kaidun.pro.activity.PhotoViewActivity;

/**
 * PageCtrl
 * Created by WangQing on 2018/1/26.
 */

public class PageCtrl {

    /**
     * 启动 图片
     * @param context   context
     * @param picPath   picPath
     */
    public static void startPhotoView(Context context, String picPath) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.FLAG_PIC_PATH_KEY, picPath);
        context.startActivity(intent);
    }
}
