package com.kaidun.pro.home;

import android.os.Bundle;
import android.view.View;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initListener() {

    }
}
