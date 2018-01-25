package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kaidun.pro.R;
import com.kaidun.pro.adapter.MsgFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class MsgFragment extends BaseFragment {

    public static final String KEY = "key";

    @BindView(R.id.vp_inside_msg)
    ViewPager mMsgVp;
    @BindView(R.id.msg_type_tab)
    TabLayout mMsgTab;

    public static MsgFragment newInstance(int navType) {
        MsgFragment fragment = new MsgFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, navType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        MsgFragmentPagerAdapter pagerAdapter = new MsgFragmentPagerAdapter(getChildFragmentManager(),
                new Fragment[]{MsgReadFragment.newInstance(),
                        MsgUnreadFragment.newInstance()});
        pagerAdapter.setTitles(getResources().getStringArray(R.array.msg_title));
        mMsgVp.setAdapter(pagerAdapter);
        mMsgTab.setupWithViewPager(mMsgVp);
    }


    @Override
    public void initData(Bundle bundle1) {

    }

    @Override
    public void initListener() {

    }

}
