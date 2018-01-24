package com.kaidun.pro.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kaidun.pro.R;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubVideoFragment extends BaseFragment {

    private RecyclerView rvVideo;

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
        Bundle arguments = getArguments();

    }

    @Override
    public void initListener() {

    }


}
