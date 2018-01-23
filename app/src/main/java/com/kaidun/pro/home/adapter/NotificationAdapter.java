package com.kaidun.pro.home.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.kaidun.pro.home.bean.Notification;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;

/**
 * Created by Administrator on 2018/1/22.
 */

public class NotificationAdapter extends ZKAdapter<Notification, NotificationHolder> {

    public NotificationAdapter(int layoutResId, @Nullable List<Notification> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NotificationHolder helper, Notification item) {
        helper.setData(item);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.showOrHide(position);
    }
}
