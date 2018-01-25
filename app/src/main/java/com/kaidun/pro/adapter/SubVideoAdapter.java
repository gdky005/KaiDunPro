package com.kaidun.pro.adapter;

import android.content.Context;
import android.view.View;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.SubVideoBean;

import java.util.List;

/**
 * author chmyy
 * created on 2018/1/25
 * email fat_chao@163.com.
 */

public class SubVideoAdapter extends RvAdapter<SubVideoBean> {
    public SubVideoAdapter(Context context, List<SubVideoBean> list, RvListener listener) {
        super(context, list, listener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_sub_video;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new SubVideoHolder(view, viewType, listener);
    }
}
