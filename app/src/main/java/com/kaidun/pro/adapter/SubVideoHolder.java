package com.kaidun.pro.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.SubVideoBean;
import com.kaidun.pro.views.ZKImageView;

/**
 * author chmyy
 * created on 2018/1/25
 * email fat_chao@163.com.
 */

public class SubVideoHolder extends RvHolder<SubVideoBean> {

    private TextView tvTitle;
    private ZKImageView zkImageView;

    public SubVideoHolder(View itemView, int type, RvListener listener) {
        super(itemView, type, listener);
        switch (type) {
            case -1:
                tvTitle = itemView.findViewById(R.id.tv_video);
                zkImageView = itemView.findViewById(R.id.iv_sub);
                break;
            case 0:
                break;

        }

    }

    @Override
    public void bindHolder(SubVideoBean dvdListBean, int position) {
        String tageTitle = dvdListBean.getTageTitle();
        String thumbnallUrl = dvdListBean.getThumbnallUrl();
        if (!TextUtils.isEmpty(tageTitle) && tvTitle != null)
            tvTitle.setText(tageTitle);
        if (!TextUtils.isEmpty(thumbnallUrl) && zkImageView != null)
            zkImageView.setImageURI(thumbnallUrl);

    }
}
