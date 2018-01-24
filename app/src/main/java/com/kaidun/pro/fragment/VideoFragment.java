package com.kaidun.pro.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kaidun.pro.R;
import com.kaidun.pro.adapter.VideoFragmentAdapter;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private TabLayout tabPackage;
    private ViewPager vpVideo;
    public static final String KEY = "key";


    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView(View view) {
        tabPackage = view.findViewById(R.id.tab_package);
        vpVideo = view.findViewById(R.id.vp_video);
        VideoFragmentAdapter pagerAdapter = new VideoFragmentAdapter(getChildFragmentManager(),
                new Fragment[]{new SubVideoFragment(),
                        new SubVideoFragment(), new SubVideoFragment()});
        vpVideo.setAdapter(pagerAdapter);
        pagerAdapter.setTitles(new String[]{"ABC", "LA", "SC"});
        tabPackage.setupWithViewPager(vpVideo);

    }

    public static VideoFragment newInstance(int navType) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, navType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
