package com.kaidun.pro.home.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.home.bean.Recommended;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Administrator on 2018/1/22.
 */

public class RecommendedAdapter extends ZKAdapter<Recommended, RecommendedHolder> {

    public RecommendedAdapter(int layoutResId, @Nullable List<Recommended> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(RecommendedHolder helper, Recommended item) {
        helper.setData(item);
    }

    @Override
    public void onBindViewHolder(RecommendedHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.showOrHide(position);
    }
}
