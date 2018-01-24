package com.kaidun.pro;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kaidun.pro.adapter.MsgDetailAdapter;
import com.kaidun.pro.bean.SwipeBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by lmj on 2018/1/23.
 */

public class MesDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.msg_recycler)
    RecyclerView mMsgDetailRecycler;
    private ArrayList<SwipeBean> mData;
    private MsgDetailAdapter msgDetailAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.msg_detail_layout;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        initDemoData();
        msgDetailAdapter = new MsgDetailAdapter(mData, this);
        mMsgDetailRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMsgDetailRecycler.addItemDecoration(new RecDividerItemDecoration(getResources().getColor(R.color.text_third),1));
        mMsgDetailRecycler.setAdapter(msgDetailAdapter);
        mToolbarTitle.setText(R.string.msg_detail);
    }

    private void initDemoData() {
        mData = new ArrayList<SwipeBean>(10);
        String content = "推荐了 Jam 小朋友加入凯顿幼儿英语。凯顿幼儿美语推出新课程了，欢迎各位家长带孩子来体验，活动免费开放四天。";
        for (int i = 0; i < 10; i++) {
            SwipeBean bean = new SwipeBean(null, "2017/12/26",
                    "家长", content, "回复：凯顿" + i, i % 2);
            mData.add(bean);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
