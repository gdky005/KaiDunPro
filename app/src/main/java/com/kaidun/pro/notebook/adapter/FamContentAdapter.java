package com.kaidun.pro.notebook.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.notebook.bean.FamContent;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * @author Yunr
 * @date 2018/01/23 15:36
 */
public class FamContentAdapter extends ZKAdapter<FamContent, FamContentHolder> {

    public FamContentAdapter(int layoutResId, @Nullable List<FamContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FamContentHolder helper, FamContent item) {
        helper.setData(item);
    }
}
