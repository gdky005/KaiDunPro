package com.kaidun.pro.home.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Home;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeAdapter extends ZKAdapter<Home, HomeHolder> {
    private final int HEADER = 0;
    private final int BODY = 1;

    public HomeAdapter(int layoutResId, @Nullable List<Home> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeHolder helper, Home item) {
        try {
            helper.setData(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("TAG", "getItemCount()" + getItemCount());
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER) {
            return new HomeHeaderHolder(inflater.inflate(R.layout.include_school_notification,
                    parent, false));
        } else if (viewType == BODY) {
            return new HomeBodyHolder(inflater.inflate(R.layout.item_home,
                    parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return BODY;
        }
    }
}