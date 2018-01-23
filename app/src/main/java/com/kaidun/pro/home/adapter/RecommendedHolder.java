package com.kaidun.pro.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Notification;
import com.kaidun.pro.home.bean.Recommended;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by Administrator on 2018/1/22.
 */

public class RecommendedHolder extends ZKViewHolder {
    @BindView(R.id.iv_parents_avatar)
    ImageView mParentsAvatar;
    @BindView(R.id.tv_parents_name)
    TextView mParentsName;
    @BindView(R.id.tv_recommended_content)
    TextView mRecommendedContent;
    @BindView(R.id.tv_recommended_date)
    TextView mRecommendedDate;
    @BindView(R.id.vi_first_line)
    View mLine;

    public RecommendedHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Recommended recommended) {
        mParentsName.setText(recommended.name);
        mRecommendedContent.setText(recommended.content);
        mRecommendedDate.setText(recommended.date);
    }

    public void showOrHide(int position) {
        if (position == 0) {
            mLine.setVisibility(View.VISIBLE);
        } else {
            mLine.setVisibility(View.GONE);
        }
    }
}
