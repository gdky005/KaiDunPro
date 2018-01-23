package com.kaidun.pro.activity;

import android.view.View;
import android.widget.Button;

import com.kaidun.pro.R;
import com.kaidun.pro.chooserole.ChooseRoleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Doraemon on 2018/1/23.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {


    }

    @OnClick({R.id.btn_login})
    public void onViewClick(View view) {
        //TODO check
        ChooseRoleActivity.start(this);
    }

    @Override
    protected void initData() {

    }
}
