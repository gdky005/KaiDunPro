package com.kaidun.pro.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.SubVideoBean;

/**
 * author chmyy
 * created on 2018/1/25
 * email fat_chao@163.com.
 */

public class SubVideoHolder extends RvHolder<SubVideoBean> {

    private TextView tvTitle;

    public SubVideoHolder(View itemView, int type, RvListener listener) {
        super(itemView, type, listener);
        tvTitle = itemView.findViewById(R.id.tv_video);
    }

    @Override
    public void bindHolder(SubVideoBean dvdListBean, int position) {
        String tageTitle = dvdListBean.getTageTitle();
        if (!TextUtils.isEmpty(tageTitle))
            tvTitle.setText(tageTitle);
    }
}
