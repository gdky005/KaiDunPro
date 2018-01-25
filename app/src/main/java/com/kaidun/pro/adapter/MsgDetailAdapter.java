package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.SwipeBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by lmj on 2018/1/23.
 */

public class MsgDetailAdapter extends ZKAdapter<SwipeBean,ZKViewHolder>{

    public MsgDetailAdapter(@Nullable List<SwipeBean> data) {
        super(data);
    }

    @Override
    protected void convert(ZKViewHolder helper, SwipeBean item) {
        if (helper != null){
            helper.setText(R.id.tv_parents_name, item.name);
            helper.setText(R.id.tv_recommended_date, item.date);
            helper.setText(R.id.tv_recommended_content, item.content);
        }
    }
}