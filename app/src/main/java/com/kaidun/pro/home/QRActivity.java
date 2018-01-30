package com.kaidun.pro.home;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.utils.QRCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/22.
 */

public class QRActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_qr)
    ImageView mQr;
    @BindView(R.id.iv_qr_avatar)
    SimpleDraweeView mQrAvatar;
    @BindView(R.id.tv_qr_name)
    TextView mQrName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        initToolbar();
        mToolbar.setNavigationIcon(R.drawable.return_icon_home);
        mToolbarTitle.setText("二维码");

        String qrInfo = KDAccountManager.getInstance().getUserInfoBean().getAreaCode()
                + KDAccountManager.getInstance().getUserInfoBean().getStuCode();
        Bitmap bitmap = QRCodeUtils.createQRCodeBitmap(qrInfo, SizeUtils.dp2px(500));
        mQr.setImageBitmap(bitmap);
        mQrName.setText(KDAccountManager.getInstance().getUserInfoBean().getStuName());
        mQrAvatar.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
