package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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

public class MsgUnreadFragment extends BaseFragment implements MessageAdapter.onSwipeListener {

    public static final String KEY = "key";
    @BindView(R.id.unread_recle)
    RecyclerView msg_unread_recler;

    @BindView(R.id.no_msg)
    TextView mTextNoMsg;
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
//        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
//                DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        RecDividerItemDecoration decoration = new RecDividerItemDecoration(
                getResources().getColor(R.color.bg_color), DensityUtil.dipToPixels(mContext, 12));
        //divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        msg_unread_recler.addItemDecoration(decoration);
        msg_unread_recler.setLayoutManager(new LinearLayoutManager(getContext()));
        msg_unread_recler.setAdapter(messageAdapter);
        messageAdapter.setOnDelListener(this);
        initUnreadData();

    }

    private List<ReadAndUnReadBean.ResultBean> getSampleData() {
        mData = new ArrayList<ReadAndUnReadBean.ResultBean>();
        String content = "推荐了 Jam 小朋友加入凯顿幼儿英语。凯顿幼儿美语推出新课程了，欢迎各位家长带孩子来体验，活动免费开放四天。";
       /* for (int i = 0; i < 9; i++) {
            SwipeBean bean = new SwipeBean(null, "2017/12/26",
                    "家长", content, "回复：凯顿"+i);
            data.add(bean);
        }*/
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
                    mTextNoMsg.setVisibility(View.GONE);
                    mData.clear();
                    mData.addAll(data.getResult());
                    messageAdapter.notifyDataSetChanged();
                }else {
                   // mTextNoMsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                //todo 请求失败
            }
        });
        httpUtils.getReadAndUnReadMsg(Constant.FLAG_UNREAD);
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
                    initUnreadData();
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
}
