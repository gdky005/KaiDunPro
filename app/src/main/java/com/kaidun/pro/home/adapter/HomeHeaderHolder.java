package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
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

public class HomeHeaderHolder extends HomeHolder {
    @BindView(R.id.tv_school_notice_content)
    TextView mSchoolNoticeContent;
    @BindView(R.id.tv_school_notice_date)
    TextView mSchoolNoticeDate;

    public HomeHeaderHolder(View view) {
        super(view);
    }

    public void setData(Home home) {
        mSchoolNoticeContent.setText(home.schoolNoticeContent);
        mSchoolNoticeDate.setText(home.schoolNoticeDate);
    }
}
