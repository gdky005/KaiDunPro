package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.adapter.PicAdapter;
import com.kaidun.pro.bean.PicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.zhuoke.sdk.base.BaseFragment;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * PicFragment
 * Created by WangQing on 2018/1/24.
 */

public class PicFragment extends BaseFragment {
    @BindView(R.id.pic_recycle_view)
    ZKRecycleView picRecycleView;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static PicFragment newInstance() {
        PicFragment fragment = new PicFragment();
        Bundle args = new Bundle();
//        args.putInt(KEY, navType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    public void initView(View view) {
        tvTitle.setText(R.string.nav_title_picture);

        initRecyclerView();


    }

    private void initRecyclerView() {
        List<PicBean> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            PicBean itemBean = new PicBean();
            itemBean.setName("2018年01月24日 " + i);
            list.add(itemBean);
        }

        picRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        picRecycleView.setAdapter(new PicAdapter(list));
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
