package com.kaidun.pro;

import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.notebook.bean.MsgBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by lmj on 2018/1/24.
 */

public class WriteMsgActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mToolbarTitle;

    @BindView(R.id.cancel_btn)
    TextView cancel;

    @BindView(R.id.send_btn)
    TextView send;
    private KdNetWorkClient httpUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.write_msg_layout;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        cancel.setOnClickListener(this);
        send.setOnClickListener(this);
        mToolbarTitle.setText(R.string.write_msg);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initData() {
        httpUtils = new KdNetWorkClient();

    }

    @Override
    public void onClick(View view) {
        if (view == send){
            httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
                @Override
                public void getSuccessDataCallBack(MsgBean data) {

                }

                @Override
                public void getFailDataCallBack(int failIndex) {

                }
            });
            httpUtils.leaveMsg();
        }else if (view == cancel){
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }
}
