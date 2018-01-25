package com.kaidun.pro.adapter;

import android.text.TextUtils;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.VideoBean;

/**
 * author chmyy
 * created on 2018/1/25
 * email fat_chao@163.com.
 */

public class VideoHolder extends RvHolder<VideoBean.DvdListBean> {

    private SimpleDraweeView simpleDraweeView;

    public VideoHolder(View itemView, int type, RvListener listener) {
        super(itemView, type, listener);
        simpleDraweeView = itemView.findViewById(R.id.iv_video);
    }

    @Override
    public void bindHolder(VideoBean.DvdListBean dvdListBean, int position) {
        if (!TextUtils.isEmpty(dvdListBean.getBookUrl()))
            simpleDraweeView.setImageURI(dvdListBean.getBookUrl());
    }
}
