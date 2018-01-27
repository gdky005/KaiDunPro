package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Home;
import com.kaidun.pro.home.bean.SchoolNotification;

import butterknife.BindView;

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

    @SuppressLint("SetTextI18n")
    public void setData(Home home) {
        if (home instanceof SchoolNotification) {
            mSchoolNoticeContent.setText(((SchoolNotification) home).getResult().getInform().getReleaseContent());
            mSchoolNoticeDate.setText(((SchoolNotification) home).getResult().getInform().getReleaseTime()
                    + " by " + ((SchoolNotification) home).getResult().getInform().getReleaser());
        }
    }

    @Override
    public void setEmptyData() {
        mSchoolNoticeContent.setText("暂无校内通知");
        mSchoolNoticeDate.setText("");
    }
}
