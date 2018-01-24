package com.kaidun.pro.chooserole.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.chooserole.bean.ChooseRoleBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class RoleAdapter extends ZKAdapter<ChooseRoleBean, RoleHolder> {
    public RoleAdapter(int layoutResId, @Nullable List<ChooseRoleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(RoleHolder helper, ChooseRoleBean item) {
        helper.setData(item);
    }
}
