package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Home;

import butterknife.BindView;
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
}
