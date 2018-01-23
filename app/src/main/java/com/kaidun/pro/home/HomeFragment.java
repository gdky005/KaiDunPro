package com.kaidun.pro.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.adapter.HomeAdapter;
import com.kaidun.pro.home.bean.Home;

import java.util.ArrayList;

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
    @BindView(R.id.tv_school_notice_content)
    TextView mSchoolNoticeContent;
    @BindView(R.id.tv_school_notice_date)
    TextView mSchoolNoticeDate;
    @BindView(R.id.rl_home_layout)
    RecyclerView mHomeLayout;

    private HomeAdapter mAdapter;
    private ArrayList<Home> mHomes = new ArrayList<>();

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

    @SuppressLint("SetTextI18n")
    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mToolbarTitle.setText("主页");
        mParentsName.setText("Durian_");
        mParentsNick.setText("Jaaaelu");
        mSchoolNoticeContent.setText("          下次一ABC课程，我们进行考试，及成果展示，请各位家长带好xxx相关的东西，并准时参加成果展示课程。");
        mSchoolNoticeDate.setText("2017年9月6日 by Zola");
        mHomeLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(R.layout.item_home, mHomes);
        mHomeLayout.setAdapter(mAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        mHomeLayout.addItemDecoration(divider);
    }

    @Override
    public void initData(Bundle bundle) {
        Home home = new Home(null, "ABC 1-9", 1,
                0.8, 0.5, 0.3,
                "          下次一ABC课程，我们进行考试，及成果展示，请各位家长带好xxx相关的东西，并准时参加成果展示课程。",
                "2017年9月6日 by Zola");
        mHomes.add(home);
        mHomes.add(home);
        mAdapter.notifyDataSetChanged();
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
