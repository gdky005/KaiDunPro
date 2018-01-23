package com.kaidun.pro.notebook.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.kaidun.pro.notebook.bean.FamilyContact;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * @author Yunr
 * @date 2018/01/23 13:20
 */
public class NoteBookAdapter extends ZKAdapter<FamilyContact.ResultBean, NoteBookHolder> {

    public NoteBookAdapter(int layoutResId, @Nullable List<FamilyContact.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NoteBookHolder helper, FamilyContact.ResultBean item) {
        helper.setData(item);
    }
}
