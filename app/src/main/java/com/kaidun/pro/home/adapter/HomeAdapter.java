package com.kaidun.pro.home.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.home.bean.Home;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeAdapter extends ZKAdapter<Home, HomeHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<Home> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeHolder helper, Home item) {
        helper.setData(item);
    }
}
