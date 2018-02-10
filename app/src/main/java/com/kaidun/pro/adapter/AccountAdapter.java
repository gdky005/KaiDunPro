package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.AccountData;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * AccountAdapter
 * Created by Doraemon on 2018/2/9.
 */
public class AccountAdapter extends ZKAdapter<AccountData, ZKViewHolder> {
    public AccountAdapter(@Nullable List<AccountData> data) {
        super(R.layout.item_account, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, AccountData item) {
        helper.setText(R.id.tv_account, item.getUserCode());
    }
}
