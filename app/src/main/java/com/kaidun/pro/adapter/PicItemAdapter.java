package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.PageCtrl;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.PicBean;
import com.kaidun.pro.views.ZKImageView;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * PicAdapter
 * Created by WangQing on 2018/1/24.
 */
public class PicItemAdapter extends ZKAdapter<PicBean.PictureUrlMapBean, ZKViewHolder> {
    public PicItemAdapter(@Nullable List<PicBean.PictureUrlMapBean> data) {
        super(R.layout.item_pic_inner, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, PicBean.PictureUrlMapBean item) {
        String kflId = item.getKflId();
        String smallUrl = item.getSendSmallUrl();
        String teacSendUrl = item.getTeacSendUrl();

        ZKImageView zkImageView = helper.getView(R.id.iv);
        ImageView imageView = helper.getView(R.id.play_iv);

        if (isVideo(teacSendUrl)) {
            helper.setVisible(R.id.play_iv, true);
            zkImageView.setImageURI(smallUrl);
        } else {
            helper.setVisible(R.id.play_iv, false);
            zkImageView.setImageURI(teacSendUrl);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideoPlay(teacSendUrl, smallUrl);
            }
        });
        zkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpAction(teacSendUrl, smallUrl);
            }
        });
    }

    private void jumpAction(String teacSendUrl, String smallUrl) {
        //                ToastUtils.showShort("图片：" + helper.getAdapterPosition());
//                String picUrl = "http://a.hiphotos.baidu.com/image/h%3D300/sign=c17af2b3bb51f819ee25054aeab54a76/d6ca7bcb0a46f21f46612acbfd246b600d33aed5.jpg";

        if (isVideo(teacSendUrl)) {
            startVideoPlay(teacSendUrl, smallUrl);
        } else {
            PageCtrl.startPhotoView(mContext, teacSendUrl);
        }
    }

    private void startVideoPlay(String teacSendUrl, String smallUrl) {

        if (TextUtils.isEmpty(teacSendUrl)) {
            ToastUtils.showShort("播放地址有误，不能播放");
            return;
        }

        PageCtrl.startVideoPlay(mContext, getName(teacSendUrl), smallUrl, teacSendUrl);
    }

    /**
     * 是否是视频
     *
     * @param url 一个 url
     * @return 是否是视频
     */
    public boolean isVideo(String url) {
        String name = getName(url);
        //包含 .mp4 就是视频
        return !TextUtils.isEmpty(name) && name.contains(".mp4");
    }

    /**
     * 获取名字
     *
     * @param url url
     * @return 名字
     */
    public String getName(String url) {
        try {
            return FileUtils.getFileName(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
