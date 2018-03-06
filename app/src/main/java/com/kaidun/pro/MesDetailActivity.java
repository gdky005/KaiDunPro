package com.kaidun.pro;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.adapter.MsgDetailAdapter;
import com.kaidun.pro.bean.MsgDetailBean;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.views.RecDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lmj on 2018/1/23.
 */

public class MesDetailActivity extends KDBaseActivity implements View.OnClickListener, View.OnLayoutChangeListener, BaseQuickAdapter.RequestLoadMoreListener {


    public static final int REPLY = 1;
    public static final int MSG = 0;

    @BindView(R.id.reply_edit)
    EditText mReplyedit;
    @BindView(R.id.tv_title)
    TextView mToolbarTitle;
    @BindView(R.id.send_img)
    ImageView sendMsg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.msg_recycler)
    RecyclerView mMsgDetailRecycler;
    private ArrayList<MsgDetailBean.ResultBean> mData;

    private SparseIntArray layouts = new SparseIntArray(2);
    private KdNetWorkClient httpUtils;
    private String keyId = Constant.INVALID;
    private MsgDetailAdapter adapter;
    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;

    @BindView(R.id.pb_loading)
    ProgressBar mProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.msg_detail_layout;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null){
            keyId = intent.getStringExtra(Constant.KEY_ID);
        }
        rootLayout.addOnLayoutChangeListener(this);
        layouts.put(REPLY,R.layout.item_msg_detail_reply);
        layouts.put(MSG,R.layout.item_msg_detail_msg);
        httpUtils = new KdNetWorkClient();
        sendMsg.setOnClickListener(this);
        initDemoData();



        adapter = new MsgDetailAdapter(mData);
        adapter.setMultiTypeDelegate(new MultiTypeDelegate<MsgDetailBean.ResultBean>(layouts) {
            @Override
            protected int getItemType(MsgDetailBean.ResultBean bean) {
                return TextUtils.isEmpty(bean.getKmdRole()) ? MSG : REPLY;
            }
        });

        mMsgDetailRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMsgDetailRecycler.addItemDecoration(new RecDividerItemDecoration(getResources().getColor(R.color.text_third),1));
        mMsgDetailRecycler.setAdapter(adapter);
        mToolbarTitle.setText(R.string.msg_detail);
        adapter.setOnLoadMoreListener(this,mMsgDetailRecycler);
        getDetailDemo();
    }




    private void getDetailDemo() {
        if (httpUtils == null) {
            return;
        }

        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgDetailBean>() {

            @Override
            public void getSuccessDataCallBack(MsgDetailBean data) {
                if (data != null ){
                    List<MsgDetailBean.ResultBean> result = data.getResult();
                    if (result == null || result.size() <= 0){
                        showNoMsgTip();
                        return;
                    }
                    mData.clear();
                    mData.addAll(result);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        httpUtils.getMsgDetail(keyId,null);
    }

    private void showNoMsgTip() {

    }

    private void initDemoData() {
        mData = new ArrayList<MsgDetailBean.ResultBean>(10);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view == sendMsg){
            String msg = mReplyedit.getText().toString();
            if (TextUtils.isEmpty(msg)){
                ToastUtils.showShort("输入内容不能为空");
                return;
            }

            httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
                @Override
                public void getSuccessDataCallBack(MsgBean data) {
                    if (100 == data.getStatusCode()){
                        mReplyedit.setText("");
                        onLoadMoreRequested();
                        mMsgDetailRecycler.scrollToPosition(adapter.getItemCount() - 1);
                        ToastUtils.showShort("发送成功！");
                    }else {
                        ToastUtils.showShort("发送失败！");
                    }
                    sendMsg.setClickable(true);
                    mProgress.setVisibility(View.GONE);
                }

                @Override
                public void getFailDataCallBack(int failIndex) {
                    sendMsg.setClickable(true);
                    mProgress.setVisibility(View.GONE);
                }
            });
            httpUtils.sendMsgDetail(keyId, msg);
            sendMsg.setClickable(false);
            mProgress.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }

    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

    }

    @Override
    public void onLoadMoreRequested() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgDetailBean>() {

            @Override
            public void getSuccessDataCallBack(MsgDetailBean data) {
                if (data != null ){
                    List<MsgDetailBean.ResultBean> result = data.getResult();
                    if (result == null || result.size() <= 0){
                        adapter.loadMoreEnd();
                        return;
                    }
                    mData.addAll(result);
                    adapter.loadMoreComplete();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                adapter.loadMoreFail();
            }
        });
        httpUtils.getMsgDetail(keyId,mData.get(mData.size() -1).getKmdCode());
    }


}
