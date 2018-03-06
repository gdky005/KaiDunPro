package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.kaidun.pro.Constant;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.MessageAdapter;
import com.kaidun.pro.bean.ReadAndUnReadBean;
import com.kaidun.pro.bean.SwipeBean;
import com.kaidun.pro.KdNetWorkClient;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseFragment;

/**
 *
 * Created by WangQing on 2018/1/22.
 */

public class MsgReadFragment extends BaseFragment implements EasyRefreshLayout.EasyEvent, MessageAdapter.onSwipeListener, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String KEY = "key";


    @BindView(R.id.refresh_layout)
    EasyRefreshLayout mRefreshLayout;
    @BindView(R.id.read_recle)
    RecyclerView msg_read_recler;
    private MessageAdapter messageAdapter;
    private KdNetWorkClient httpUtils;
    private ArrayList<ReadAndUnReadBean.ResultBean> mData;
    private boolean isRefresh;


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
        ButterKnife.bind(this, view);

        messageAdapter = new MessageAdapter(getSampleData(), R.layout.item_msg_read);
        messageAdapter.setOnDelListener(this);
        RecDividerItemDecoration decoration = new RecDividerItemDecoration(
                getResources().getColor(R.color.bg_color), DensityUtil.dipToPixels(mContext, 12));
        msg_read_recler.addItemDecoration(decoration);
        msg_read_recler.setLayoutManager(new LinearLayoutManager(getContext()));
        msg_read_recler.setAdapter(messageAdapter);
        httpUtils = new KdNetWorkClient();
        messageAdapter.setOnLoadMoreListener(this,msg_read_recler);
        mRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        mRefreshLayout.addEasyEvent(this);
    }

    private List<ReadAndUnReadBean.ResultBean> getSampleData() {
        mData = new ArrayList<>();
        return mData;
    }

    @Override
    public void onResume() {
        super.onResume();
        getReadMsg();
    }

    private void getReadMsg() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ReadAndUnReadBean>() {
            @Override
            public void getSuccessDataCallBack(ReadAndUnReadBean data) {
                if (data.getResult() != null) {
                    mData.clear();

                    mData.addAll(data.getResult());
                    if (isRefresh) {
                        messageAdapter.setNewData(mData);
                        mRefreshLayout.refreshComplete();
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
                    mRefreshLayout.refreshComplete();
                }
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_READ,null);
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
        if (httpUtils != null) {
            httpUtils.setmCallBack(null);
            httpUtils = null;
        }
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onRefreshing() {
        isRefresh = true;
        getReadMsg();
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
                //todo 请求失败
                messageAdapter.loadMoreFail();
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_READ, mData.get(mData.size() - 1).getKfmCode());
    }
}
