package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.PicBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * PicAdapter
 * Created by WangQing on 2018/1/24.
 */
public class PicAdapter extends ZKAdapter<PicBean, ZKViewHolder> {
    public PicAdapter(@Nullable List<PicBean> data) {
        super(R.layout.item_pic, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, PicBean item) {

        helper.setText(R.id.name, item.getName());
    }
}
