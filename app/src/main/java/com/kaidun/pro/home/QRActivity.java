package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/22.
 */

public class QRActivity extends BaseActivity {
    @BindView(R.id.tv_toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_qr)
    ImageView mQr;
    @BindView(R.id.iv_qr_avatar)
    ImageView mQrAvatar;
    @BindView(R.id.tv_qr_name)
    TextView mQrName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        mQrName.setText("Durian_");
        initToolbar();
        mToolbarTitle.setText("二维码");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
