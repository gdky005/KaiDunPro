package com.kaidun.pro.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setTitle("预览大图");
    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String picUrl = intent.getStringExtra(FLAG_PIC_PATH_KEY);


        if (!TextUtils.isEmpty(picUrl)) {
            Uri uri = Uri.parse(picUrl);

            zkImageView.setPhotoUri(uri);

            zkImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Bitmap bitmap = ImgUtils.getBitmap(picUrl);
                    boolean b = ImgUtils.saveImageToGallery(mContext, bitmap);

                    if (b) {
                        ToastUtils.showShort("保存图片到本地成功");
                    } else {
                        ToastUtils.showShort("保存图片到本地失败");
                    }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
