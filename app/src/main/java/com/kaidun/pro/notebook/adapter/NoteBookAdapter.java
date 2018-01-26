package com.kaidun.pro.notebook.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.notebook.bean.FamContact;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * @author Yunr
 * @date 2018/01/23 13:20
 */
public class NoteBookAdapter extends ZKAdapter<FamContact, NoteBookHolder> {

    public NoteBookAdapter(int layoutResId, @Nullable List<FamContact> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NoteBookHolder helper, FamContact item) {
        helper.setData(item);
    }
}
