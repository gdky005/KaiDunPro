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
            // TODO: 2018/1/27 请处理这里 ,按照自己的逻辑处理。
            mSchoolNoticeContent.setText(((SchoolNotification) home).getResult().getComment().getComment());
            mSchoolNoticeDate.setText(((SchoolNotification) home).getResult().getComment().getTeacher()
                    + " by " + ((SchoolNotification) home).getResult().getComment().getPublishTime());
        }
    }
}
