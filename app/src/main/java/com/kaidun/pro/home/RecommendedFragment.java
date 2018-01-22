package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.adapter.RecommendedAdapter;
import com.kaidun.pro.home.bean.Recommended;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class RecommendedFragment extends BaseFragment {
    @BindView(R.id.rv_show_recommended)
    RecyclerView mShowRecommended;
    private RecommendedAdapter mAdapter;
    private List<Recommended> mRecommendeds = new ArrayList<>();

    public static RecommendedFragment newInstance() {

        Bundle args = new Bundle();

        RecommendedFragment fragment = new RecommendedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommended;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mShowRecommended.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecommendedAdapter(R.layout.item_recommended, mRecommendeds);
        mShowRecommended.setAdapter(mAdapter);
    }

    @Override
    public void initData(Bundle bundle) {
        Recommended recommended = new Recommended(null, "家长",
                "2017/12/26", "推荐了 Jam 小朋友加入凯顿幼儿英语。");
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mRecommendeds.add(recommended);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {

    }
}
