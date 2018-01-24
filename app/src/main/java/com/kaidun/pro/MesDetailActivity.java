package com.kaidun.pro;

import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by lmj on 2018/1/23.
 */

public class MesDetailActivity extends BaseActivity{


    /*@BindView(R.id.msg_detail_recycler)
    RecyclerView mMsgDetailRecycler;*/


    @Override
    protected int getLayoutId() {
        return R.layout.msg_detail_layout;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
