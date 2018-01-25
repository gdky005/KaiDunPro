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
    @BindView(R.id.vi_first_line)
    View mLine;

    public NotificationHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Notification.ResultBean notification) {
        mNotificationDate.setText(notification.getKpmDate());
        mNotificationContent.setText(notification.getKpmContextMessage());
        mNotificationType.setText(notification.getKpmTitle());
    }

    void showOrHide(int position) {
        if (position == 0) {
            mLine.setVisibility(View.VISIBLE);
        } else {
            mLine.setVisibility(View.GONE);
        }
    }
}
