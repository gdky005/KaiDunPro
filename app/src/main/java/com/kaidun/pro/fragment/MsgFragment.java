package com.kaidun.pro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.WriteMsgActivity;
import com.kaidun.pro.adapter.MsgFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class MsgFragment extends BaseFragment implements View.OnClickListener {

    public static final String KEY = "key";

    @BindView(R.id.tv_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_inside_msg)
    ViewPager mMsgVp;
    @BindView(R.id.msg_type_tab)
    TabLayout mMsgTab;
    @BindView(R.id.write_btn)
    ImageView writeBtn;

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
        mToolbarTitle.setText(R.string.msg_title);

        mMsgTab.setupWithViewPager(mMsgVp);
        writeBtn.setOnClickListener(this);
    }

//    private void initToolBar() {
//        this.mToolbar.inflateMenu(R.layout.write_icon_layout);
//        this.mToolbar.setOnMenuItemClickListener(this);
//    }

    @Override
    public void initData(Bundle bundle1) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view) {
        if (view == writeBtn){
            getActivity().startActivity(new Intent(getActivity(),WriteMsgActivity.class));
        }
    }
}
