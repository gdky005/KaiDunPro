package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.kaidun.pro.Constant;
import com.kaidun.pro.KdNetWorkClient;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.MessageAdapter;
import com.kaidun.pro.bean.ReadAndUnReadBean;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class MsgUnreadFragment extends BaseFragment implements MessageAdapter.onSwipeListener, BaseQuickAdapter.UpFetchListener, EasyRefreshLayout.EasyEvent, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String MSG_UNREAD_FRAGMENT_UPDATE_MESSAGE = "msg_unread_fragment_update_message";

    public static final String KEY = "key";
    @BindView(R.id.unread_recle)
    RecyclerView msg_unread_recler;

    @BindView(R.id.refresh_layout)
    EasyRefreshLayout refreshLayout;

    private MessageAdapter messageAdapter;
    private ArrayList<ReadAndUnReadBean.ResultBean> mData;
    private KdNetWorkClient httpUtils;
    private boolean isRefresh;


    public static MsgUnreadFragment newInstance() {
        MsgUnreadFragment fragment = new MsgUnreadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_msg_unread;
    }

    @Override
    public void initView(View view) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);
        httpUtils = new KdNetWorkClient();

        messageAdapter = new MessageAdapter(getSampleData(),R.layout.item_msg_unread);
        messageAdapter.setUpFetchEnable(true);
        messageAdapter.setUpFetchListener(this);
        RecDividerItemDecoration decoration = new RecDividerItemDecoration(
                getResources().getColor(R.color.bg_color), DensityUtil.dipToPixels(mContext, 12));
        msg_unread_recler.addItemDecoration(decoration);

        msg_unread_recler.setLayoutManager(new LinearLayoutManager(getContext()));
        msg_unread_recler.setAdapter(messageAdapter);
        messageAdapter.setOnDelListener(this);

        messageAdapter.setOnLoadMoreListener(this,msg_unread_recler);
        refreshLayout.addEasyEvent(this);
        refreshLayout.setLoadMoreModel(LoadModel.NONE);

    }

    @Override
    public void onResume() {
        super.onResume();
        initUnreadData();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private List<ReadAndUnReadBean.ResultBean> getSampleData() {
        mData = new ArrayList<ReadAndUnReadBean.ResultBean>();
        return mData;
    }

    @Override
    public void initData(Bundle bundle1) {

    }

    @Subscriber(tag = MSG_UNREAD_FRAGMENT_UPDATE_MESSAGE)
    private void initUnreadData(String state) {
        initUnreadData();
    }
    private void initUnreadData() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ReadAndUnReadBean>() {
            @Override
            public void getSuccessDataCallBack(ReadAndUnReadBean data) {
                if (data.getResult() != null) {
                    mData.clear();

                    mData.addAll(data.getResult());
                    if (isRefresh) {
                        refreshLayout.refreshComplete();
                        messageAdapter.setNewData(mData);   //重新开启上拉加载更多
                    } else {
                        messageAdapter.notifyDataSetChanged();
                    }
                }
                isRefresh = false;
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                if (isRefresh){
                    isRefresh = false;
                    refreshLayout.refreshComplete();
                }
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_UNREAD,null);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDel(int pos) {
        String kfmId = mData.get(pos).getKfmId();

        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
            @Override
            public void getSuccessDataCallBack(MsgBean data) {
                if (data != null && 100 == data.getStatusCode()){
                    mData.remove(pos);
                    messageAdapter.notifyDataSetChanged();
                    ToastUtils.showShort("删除成功");
                }else {
                    ToastUtils.showShort("删除失败");
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        httpUtils.udpateMessage(kfmId);
    }

    @Override
    public void onTop(int pos) {
    }

    @Override
    public void onUpFetch() {

    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onRefreshing() {
        isRefresh = true;
        initUnreadData();
    }

    @Override
    public void onLoadMoreRequested() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ReadAndUnReadBean>() {
            @Override
            public void getSuccessDataCallBack(ReadAndUnReadBean data) {
                if (data != null) {
                    List<ReadAndUnReadBean.ResultBean> result = data.getResult();
                    if (result == null || result.size() <= 0) {
                        messageAdapter.loadMoreEnd();
                        return;
                    }
                    mData.addAll(result);
                    messageAdapter.loadMoreComplete();
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                messageAdapter.loadMoreFail();
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_UNREAD,mData.get(mData.size() -1).getKfmCode());
    }
}
