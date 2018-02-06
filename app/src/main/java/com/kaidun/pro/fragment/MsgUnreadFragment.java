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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class MsgUnreadFragment extends BaseFragment implements MessageAdapter.onSwipeListener, BaseQuickAdapter.UpFetchListener, EasyRefreshLayout.EasyEvent {

    public static final String KEY = "key";
    @BindView(R.id.unread_recle)
    RecyclerView msg_unread_recler;

    @BindView(R.id.refresh_layout)
    EasyRefreshLayout refreshLayout;

    private MessageAdapter messageAdapter;
    private ArrayList<ReadAndUnReadBean.ResultBean> mData;
    private KdNetWorkClient httpUtils;


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

        refreshLayout.addEasyEvent(this);
        refreshLayout.setLoadMoreModel(LoadModel.COMMON_MODEL);

    }

    @Override
    public void onResume() {
        super.onResume();
        initUnreadData();
    }

    private List<ReadAndUnReadBean.ResultBean> getSampleData() {
        mData = new ArrayList<ReadAndUnReadBean.ResultBean>();
        return mData;
    }

    @Override
    public void initData(Bundle bundle1) {

    }

    private void initUnreadData() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ReadAndUnReadBean>() {
            @Override
            public void getSuccessDataCallBack(ReadAndUnReadBean data) {
                if (data.getResult() != null && data.getResult().size() > 0){
                    mData.clear();
                    refreshLayout.refreshComplete();
                    mData.addAll(data.getResult());
                    messageAdapter.notifyDataSetChanged();
                }else {
                   // mTextNoMsg.setVisibility(View.VISIBLE);
                    refreshLayout.refreshComplete();

                    // TODO: 2018/2/6  这里说明刷新的数据已经没有 未读消息了，需要清除
                    mData.clear();
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                //todo 请求失败
                refreshLayout.refreshComplete();
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
//                    initUnreadData();
                    mData.remove(pos);
                    messageAdapter.notifyItemRemoved(pos);
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
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ReadAndUnReadBean>() {
            @Override
            public void getSuccessDataCallBack(ReadAndUnReadBean data) {
                if (data.getResult() != null && data.getResult().size() > 0){
                    mData.addAll(data.getResult());
                    messageAdapter.notifyDataSetChanged();
                    refreshLayout.loadMoreComplete();
                }else {
                    refreshLayout.loadMoreComplete();
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                //todo 请求失败
                refreshLayout.loadMoreFail();
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_READ,mData.get(mData.size() -1).getKfmCode());
    }

    @Override
    public void onRefreshing() {
        initUnreadData();
    }
}
