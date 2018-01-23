package com.kaidun.pro.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaidun.pro.MainActivity;
import com.kaidun.pro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_parents_avatar)
    ImageView mParentsAvatar;
    @BindView(R.id.tv_parents_name)
    TextView mParentsName;
    @BindView(R.id.tv_parents_nick)
    TextView mParentsNick;
    @BindView(R.id.ll_show_stand_inside_letter)
    LinearLayout mShowStandInsideLetter;
    @BindView(R.id.ll_show_recommended)
    LinearLayout mShowRecommended;
    @BindView(R.id.ll_show_qr)
    LinearLayout mShowQr;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mToolbarTitle.setText("主页");
        mParentsName.setText("Durian_");
        mParentsNick.setText("Jaaaelu");
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.ll_show_stand_inside_letter, R.id.ll_show_recommended, R.id.ll_show_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_show_stand_inside_letter:
                startActivity(new Intent(getActivity(), StandInsideLetterActivity.class));
                break;
            case R.id.ll_show_recommended:
                RecommendedDialogFragment dialogFragment = RecommendedDialogFragment.newInstance();
                dialogFragment.show(getChildFragmentManager(), "");
                break;
            case R.id.ll_show_qr:
                startActivity(new Intent(getActivity(), QRActivity.class));
                break;
        }
    }
}
