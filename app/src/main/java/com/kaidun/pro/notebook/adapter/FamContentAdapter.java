package com.kaidun.pro.notebook.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.notebook.bean.FamContact;
import com.kaidun.pro.notebook.bean.FamContent;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * @author Yunr
 * @date 2018/01/23 15:36
 */
public class FamContentAdapter extends ZKAdapter<FamContent, FamContentHolder> {
    private FamContact famContact;

    public FamContentAdapter(int layoutResId, @Nullable List<FamContent> data, FamContact famContact) {
        super(layoutResId, data);
        this.famContact = famContact;
    }

    @Override
    protected void convert(FamContentHolder helper, FamContent item) {
        helper.setFamBookData(famContact);
        helper.setData(item);
    }
}
