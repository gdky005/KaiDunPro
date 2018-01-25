package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.PicBean;
import com.kaidun.pro.views.ZKImageView;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * PicAdapter
 * Created by WangQing on 2018/1/24.
 */
public class PicItemAdapter extends ZKAdapter<PicBean.PictureUrlMapBean, ZKViewHolder> {
    public PicItemAdapter(@Nullable List<PicBean.PictureUrlMapBean> data) {
        super(R.layout.item_pic_inner, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, PicBean.PictureUrlMapBean item) {
        ZKImageView zkImageView = helper.getView(R.id.iv);

        zkImageView.setImageURI(item.getDvdUrl());
    }
}
