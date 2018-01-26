package com.kaidun.pro.activity;

import android.content.Intent;

import com.kaidun.pro.R;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import team.zhuoke.sdk.base.BaseActivity;

public class VideoPlayActivity extends BaseActivity {
    private JZVideoPlayerStandard player;
    private String url = "http://zkteam.cc/movies/%E6%9B%9D%E9%99%88%E4%BC%9F%E9%9C%86%E7%83%AD%E5%B7%B4%E6%9C%89%E8%BF%87%E5%9C%B0%E4%B8%8B%E6%83%85%20%E5%9B%A0%E5%B7%B4%E8%A5%BF%E5%A5%B3%E6%A8%A1%E7%89%B9%E8%80%8C%E5%88%86%E6%89%8B%20180106.mp4";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews() {
        player = findViewById(R.id.videoplayer);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
       // String url = intent.getStringExtra("url");
        setTitle(name);
        player.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, name);
        player.thumbImageView.setBackgroundResource(R.mipmap.img_picture);

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
