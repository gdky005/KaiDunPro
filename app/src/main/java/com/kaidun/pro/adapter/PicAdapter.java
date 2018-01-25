package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.PicBean;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKRecycleView;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * PicAdapter
 * Created by WangQing on 2018/1/24.
 */
public class PicAdapter extends ZKAdapter<PicBean, ZKViewHolder> {
    public PicAdapter(@Nullable List<PicBean> data) {
        super(R.layout.item_pic, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, PicBean item) {
        helper.setText(R.id.time, item.getUploadTime());

        ZKRecycleView itemRecycleView = helper.getView(R.id.item_recycle_view);

//        List<PicBean> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            PicBean itemBean = new PicBean();
////            itemBean.setName("2018年01月24日 ");
//            list.add(itemBean);
//        }

        itemRecycleView.setLayoutManager(new GridLayoutManager(mContext, 4));
        itemRecycleView.setAdapter(new PicItemAdapter(item.getPictureUrlMap()));
    }
}
