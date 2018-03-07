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

public class VideoPlayActivity extends KDBaseActivity {

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
    private String smallUrl = "";
    private String mName;
    private long mEnqueue = -1L;
    private String mVideoUrl;

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
    protected void initData() {
        Intent intent = getIntent();
        mName = intent.getStringExtra(FLAG_VIDEO_NAME);
        String imageUrl = intent.getStringExtra(FLAG_VIDEO_THUMB_URL);//缩略图
        //视频播放的链接
        mVideoUrl = intent.getStringExtra(FLAG_VIDEO_URL);
        // TODO: 2018/2/9  需求说这里默认显示 『视频』 两个字
        setTitle(getString(R.string.video_text));
        if (TextUtils.isEmpty(mVideoUrl))
            mVideoUrl = "video";
        player.setUp(mVideoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, mName);
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

    @Override
    protected void initListener() {
        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals("video", mVideoUrl))
                    return;

                if (mEnqueue < 0) {
                    ToastUtils.showShort(mName + "正在下载，可在通知栏查看进度");
                    downLoadVideo(mVideoUrl);
                } else
                    ToastUtils.showShort(mName + "正在下载中，不能重复下载");
            }
        });
    }
}
