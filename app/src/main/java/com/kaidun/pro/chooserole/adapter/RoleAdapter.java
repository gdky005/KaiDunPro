package com.kaidun.pro.chooserole.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.bean.FamilyRoleBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class RoleAdapter extends ZKAdapter<FamilyRoleBean.ResultBean, RoleHolder> {
    public RoleAdapter(int layoutResId, @Nullable List<FamilyRoleBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(RoleHolder helper, FamilyRoleBean.ResultBean item) {
        helper.setData(item);
    }
}
