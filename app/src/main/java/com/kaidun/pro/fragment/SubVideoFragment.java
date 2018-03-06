package com.kaidun.pro.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kaidun.pro.R;
import com.kaidun.pro.activity.VideoDetailActivity;
import com.kaidun.pro.adapter.RvListener;
import com.kaidun.pro.adapter.VideoAdapter;
import com.kaidun.pro.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubVideoFragment extends BaseFragment {

    private RecyclerView rvVideo;
    private VideoAdapter videoAdapter;
    private List<VideoBean.DvdListBean> dvdList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sub_video;
    }

    @Override
    public void initView(View view) {
        rvVideo = view.findViewById(R.id.rv_video);
    }

    @Override
    public void initData(Bundle bundle) {
        VideoBean videoBean = getArguments().getParcelable("sub");
        dvdList.clear();
        dvdList.addAll(videoBean.getDvdList());
        videoAdapter = new VideoAdapter(mContext, dvdList, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                //TODO 点击事件
                Intent intent = new Intent();
                intent.setClass(mContext, VideoDetailActivity.class);
                intent.putExtra("id", dvdList.get(position).getCourseSortId());
                intent.putExtra("name", dvdList.get(position).getCourseSortName());
                intent.putExtra("code", dvdList.get(position).getBookCode());
                startActivity(intent);

            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        rvVideo.setLayoutManager(gridLayoutManager);
        rvVideo.setAdapter(videoAdapter);


    }

    @Override
    public void initListener() {

    }


}
