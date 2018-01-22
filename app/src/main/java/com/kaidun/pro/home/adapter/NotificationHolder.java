package com.kaidun.pro.home.adapter;

import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Notification;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by Administrator on 2018/1/22.
 */

public class NotificationHolder extends ZKViewHolder {
    @BindView(R.id.tv_notification_content)
    TextView mNotificationContent;
    @BindView(R.id.tv_notification_date)
    TextView mNotificationDate;
    @BindView(R.id.tv_notification_type)
    TextView mNotificationType;

    public NotificationHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Notification notification) {
        mNotificationDate.setText(notification.date);
        mNotificationContent.setText(notification.content);
        mNotificationType.setText(notification.contentType);
    }
}
