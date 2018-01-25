package com.kaidun.pro;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.kaidun.pro.adapter.MsgDetailAdapter;
import com.kaidun.pro.bean.SwipeBean;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by lmj on 2018/1/23.
 */

public class MesDetailActivity extends BaseActivity implements View.OnClickListener {


    public static final int REPLY = 1;
    public static final int MSG = 0;


    @BindView(R.id.send_img)
    ImageView sendMsg;
    @BindView(R.id.msg_recycler)
    RecyclerView mMsgDetailRecycler;
    private ArrayList<SwipeBean> mData;

    private SparseIntArray layouts = new SparseIntArray(2);
    private KdNetWorkClient httpUtils;


    @Override
    protected int getLayoutId() {
        return R.layout.msg_detail_layout;
    }

    @Override
    protected void initViews() {
        layouts.put(REPLY, R.layout.item_msg_detail_reply);
        layouts.put(MSG, R.layout.item_msg_detail_msg);
        httpUtils = new KdNetWorkClient();
        ButterKnife.bind(this);
        sendMsg.setOnClickListener(this);
        initDemoData();


        MsgDetailAdapter adapter = new MsgDetailAdapter(mData);
        adapter.setMultiTypeDelegate(new MultiTypeDelegate<SwipeBean>(layouts) {
            @Override
            protected int getItemType(SwipeBean swipeBean) {
                return swipeBean.type;
            }
        });

        mMsgDetailRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMsgDetailRecycler.addItemDecoration(new RecDividerItemDecoration(getResources().getColor(R.color.text_third), 1));
        mMsgDetailRecycler.setAdapter(adapter);
        setTitle(R.string.msg_detail);
        getDetailDemo();
    }

    private void getDetailDemo() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {

            @Override
            public void getSuccessDataCallBack(MsgBean data) {

            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        httpUtils.getMsgDetail("");
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

    @Override
    public void onClick(View view) {
        if (view == sendMsg) {
            httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<SwipeBean>() {
                @Override
                public void getSuccessDataCallBack(SwipeBean data) {

                }

                @Override
                public void getFailDataCallBack(int failIndex) {

                }
            });
            httpUtils.sendMsgDetail("");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }
}
