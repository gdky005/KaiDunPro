package com.kaidun.pro.adapter;

import android.content.Intent;
import android.view.View;

import com.kaidun.pro.MesDetailActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.SwipeBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;


public class MessageAdapter extends ZKAdapter<SwipeBean, ZKViewHolder> {


    private List<SwipeBean> mDatas;

    public MessageAdapter(List<SwipeBean> mDatas, int resId) {
        super(resId, mDatas);
        // int resId = FLAG == UNREAD ? R.layout.item_msg_unread : R.layout.item_msg_read;
        this.mDatas = mDatas;
    }

    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    @Override
    protected void convert(ZKViewHolder helper, SwipeBean item) {

        helper.setText(R.id.tv_parents_name, item.name);
        helper.setText(R.id.tv_recommended_date, item.date);
        helper.setText(R.id.replay_latest, item.replyContent);
        helper.setText(R.id.tv_recommended_content, item.content);

        View btnDelete = helper.getView(R.id.btnDelete);
        if (btnDelete != null) {
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mOnSwipeListener) {
                        mOnSwipeListener.onDel(helper.getAdapterPosition());
                    }
                }
            });

        }

        (helper.getView(R.id.content)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, MesDetailActivity.class));
            }
        });

        if (mLayoutResId == R.layout.item_msg_unread) {
            helper.getView(R.id.xxtx_msg).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.xxtx_msg).setVisibility(View.INVISIBLE);
        }

    }
}



