package com.kaidun.pro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.SwipeBean;

import java.util.List;

/**
 * Created by lmj on 2018/1/23.
 */

public class MsgDetailAdapter extends RecyclerView.Adapter<MsgDetailViewHolder> {


    private LayoutInflater mInflater;
    private List<SwipeBean> mData;
    private Context mContext;
    public static final int REPLY = 1;
    public static final int MSG = 0;

    public MsgDetailAdapter(List<SwipeBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public MsgDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MsgDetailViewHolder mVholder = null;
        if (viewType == MSG){
            View view = mInflater.inflate(R.layout.item_msg_detail_msg, parent, false);
            mVholder = new MsgDetailViewHolder(view);
        }else if (viewType == REPLY){
            View view = mInflater.inflate(R.layout.item_msg_detail_reply, parent, false);
            mVholder = new MsgDetailViewHolder(view);
        }
        return mVholder;
    }

    @Override
    public void onBindViewHolder(MsgDetailViewHolder holder, int position) {
        if (holder != null){
            SwipeBean bean = mData.get(position);
            holder.getTv(R.id.tv_parents_name).setText(bean.name);
            holder.getTv(R.id.tv_recommended_date).setText(bean.date);
            holder.getTv(R.id.tv_recommended_content).setText(bean.content);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).type;
    }
}
