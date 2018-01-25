package com.kaidun.pro.activity;

import android.content.Intent;
import android.widget.TextView;

import com.kaidun.pro.R;

import team.zhuoke.sdk.base.BaseActivity;

public class VideoPlayActivity extends BaseActivity {
    private TextView tvPlay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews() {
        tvPlay = findViewById(R.id.tv_play);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String url = intent.getStringExtra("url");
        setTitle(name);
        tvPlay.setText(name + "\n" + url);

    }
}
