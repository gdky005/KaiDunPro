package com.kaidun.pro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lmj on 2018/1/23.
 */

public class MsgDetailViewHolder extends RecyclerView.ViewHolder {


    public MsgDetailViewHolder(View itemView) {
        super(itemView);
    }

    public TextView getTv(int resId){
        return ((TextView) itemView.findViewById(resId));
    }

    public ImageView getImg(int resId){
        return ((ImageView) itemView.findViewById(resId));
    }

}
