package com.kaidun.pro.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.squareup.picasso.Picasso;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import team.zhuoke.sdk.base.BaseActivity;

public class VideoPlayActivity extends BaseActivity {

    /**
     * 视频的名称
     */
    public static final String FLAG_VIDEO_NAME = "name";
    /**
     * 视频的缩略图
     */
    public static final String FLAG_VIDEO_THUMB_URL = "url";
    /**
     * 视频的播放 url
     */
    public static final String FLAG_VIDEO_URL = "videoUrl";

    private JZVideoPlayerStandard player;
    private ImageView ivDownload;
    private String smallUrl = "http://zkteam.cc/movies/%E6%9B%9D%E9%99%88%E4%BC%9F%E9%9C%86%E7%83%AD%E5%B7%B4%E6%9C%89%E8%BF%87%E5%9C%B0%E4%B8%8B%E6%83%85%20%E5%9B%A0%E5%B7%B4%E8%A5%BF%E5%A5%B3%E6%A8%A1%E7%89%B9%E8%80%8C%E5%88%86%E6%89%8B%20180106.mp4";
    //    private String smallUrl = "http://211.152.60.252:8088/course/ABC/FB1/mv/FB1_1_1.mp4";
    private String mName;
    private long mEnqueue = -1L;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews() {
        player = findViewById(R.id.videoplayer);
        ivDownload = findViewById(R.id.iv_download);

    }

    @Override
    protected void initListener() {
        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEnqueue < 0) {
                    ToastUtils.showShort(mName + "正在下载，可在通知栏查看进度");
                    downLoadVideo(smallUrl);

                } else
                    ToastUtils.showShort(mName + "正在下载中，不能重复下载");
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mName = intent.getStringExtra(FLAG_VIDEO_NAME);
        String imageUrl = intent.getStringExtra(FLAG_VIDEO_THUMB_URL);
        String videoUrl = intent.getStringExtra(FLAG_VIDEO_URL);
        setTitle(mName);
        if (TextUtils.isEmpty(videoUrl))
            videoUrl = "hehe";
        player.setUp(videoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, mName);
        if (TextUtils.isEmpty(smallUrl))
            smallUrl = "path";
        Picasso.with(mContext).load(imageUrl).config(Bitmap.Config.RGB_565).fit().into(player.thumbImageView);


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


    private void downLoadVideo(String url) {


        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载
        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        request.setDestinationInExternalPublicDir("/download/", mName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mEnqueue = downloadManager.enqueue(request);


    }
}
