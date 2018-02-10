package com.kaidun.pro.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.utils.ImgUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * PhotoViewActivity
 * Created by WangQing on 2018/1/26.
 */

public class PhotoViewActivity extends KDBaseActivity {

    public static final String FLAG_PIC_PATH_KEY = "flag_pic_path_key";

    @BindView(R.id.zk_image_view)
    PhotoDraweeView zkImageView;

    private String picUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setTitle(getString(R.string.photo_title));
        setRight(R.menu.item_pic);
    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
    }

    @Override
    public void onRightText(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.right_save_pic:
//                onSavePic(picUrl);
//                break;
            case R.id.right_share_pic:
                Bitmap bitmap = ImgUtils.getBitmap(picUrl);

                if (bitmap != null) {
                    String picPath = ImgUtils.saveImageToFile(mContext, bitmap);

//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain"); // 分享发送的数据类型
//                intent.putExtra(Intent.EXTRA_SUBJECT, "掌上生活"); // 分享的主题
//                intent.putExtra(Intent.EXTRA_TEXT, "掌上生活，你的日常生活护理专家！\nPocketLife,make your life in the pocket!"); // 分享的内容
//
//                startActivity(Intent.createChooser(intent, "分享"));

                    Intent intent = IntentUtils.getShareImageIntent("我分享了一个图片", picPath);
//                Intent intent = IntentUtils.getShareTextIntent("我分享了一个图片");
                    startActivity(Intent.createChooser(intent, "分享"));

                } else {
                    ToastUtils.showShort("分享图片失败，获取原图失败，请重试");
                }

                break;
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        picUrl = intent.getStringExtra(FLAG_PIC_PATH_KEY);

        if (!TextUtils.isEmpty(picUrl)) {
            Uri uri = Uri.parse(picUrl);

            zkImageView.setPhotoUri(uri);

            zkImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    boolean b = onSavePic(picUrl);
                    return b;
                }
            });


//            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
//            controller.setUri(uri);
//            controller.setOldController(zkImageView.getController());
//            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
//                @Override
//                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                    super.onFinalImageSet(id, imageInfo, animatable);
//                    if (imageInfo == null || zkImageView == null) {
//                        return;
//                    }
//                    zkImageView.update(imageInfo.getWidth(), imageInfo.getHeight());
//                }
//            });
//            zkImageView.setController(controller.build());
        } else {
            ToastUtils.showShort("图片地址为空！");
        }
    }

    private boolean onSavePic(String picUrl) {
        Bitmap bitmap = ImgUtils.getBitmap(picUrl);
        boolean b = ImgUtils.saveImageToGallery(mContext, bitmap);

        showPicToast(b);
        return b;
    }

    private void showPicToast(boolean b) {
        if (b) {
            ToastUtils.showShort("保存图片到本地成功");
        } else {
            ToastUtils.showShort("保存图片到本地失败");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
