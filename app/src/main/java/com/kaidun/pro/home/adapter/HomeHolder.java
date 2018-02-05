package com.kaidun.pro.home.adapter;

import android.view.View;

import com.kaidun.pro.home.bean.Home;

import org.json.JSONException;

import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeHolder extends ZKViewHolder {

    public HomeHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Home home) {
    }

    public void setEmptyData() {

    }

    public void setData(Home item, int itemCount) throws JSONException {
        setData(item);
    }

    public void setEmptyData(int itemCount) {
        setEmptyData();
    }
}
