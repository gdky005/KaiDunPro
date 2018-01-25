package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.fresco.helper.utils.DensityUtil;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.MessageAdapter;
import com.kaidun.pro.bean.SwipeBean;
import com.kaidun.pro.KdNetWorkClient;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class MsgReadFragment extends BaseFragment {

    public static final String KEY = "key";


    @BindView(R.id.read_recle)
    RecyclerView msg_read_recler;
    private MessageAdapter messageAdapter;
    private KdNetWorkClient httpUtils;


    public static MsgReadFragment newInstance() {
        MsgReadFragment fragment = new MsgReadFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_msg_read;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this,view);

        messageAdapter = new MessageAdapter(getSampleData(),R.layout.item_msg_read);
       /* DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);*/
        RecDividerItemDecoration decoration = new RecDividerItemDecoration(
                getResources().getColor(R.color.bg_color), DensityUtil.dipToPixels(mContext, 12));
        //divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
       msg_read_recler.addItemDecoration(decoration);
        msg_read_recler.setLayoutManager(new LinearLayoutManager(getContext()));
        msg_read_recler.setAdapter(messageAdapter);
        httpUtils = new KdNetWorkClient();
        getReadMsg();
    }

    private List<SwipeBean> getSampleData() {
        ArrayList<SwipeBean> data = new ArrayList<>();
        String content = "推荐了 Jam 小朋友加入凯顿幼儿英语。凯顿幼儿美语推出新课程了，欢迎各位家长带孩子来体验，活动免费开放四天。";
        for (int i = 0; i < 9; i++) {
            SwipeBean bean = new SwipeBean(null, "2017/12/26",
                    "家长", content, "回复：凯顿");
            data.add(bean);
        }
        return data;
    }


    private void getReadMsg(){
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
            @Override
            public void getSuccessDataCallBack(MsgBean data) {

            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        httpUtils.getReadMsg("001");
    }

    @Override
    public void initData(Bundle bundle1) {


    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }
}