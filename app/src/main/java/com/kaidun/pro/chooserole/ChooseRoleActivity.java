package com.kaidun.pro.chooserole;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.chooserole.adapter.RoleAdapter;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class ChooseRoleActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_roles)
    RecyclerView rolesRecyclerView;
    private RoleAdapter roleAdapter;
    private int curPos;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChooseRoleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_role;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        curPos = position;
        view.findViewById(R.id.iv_role).setSelected(true);
        view.findViewById(R.id.tv_role_name).setSelected(true);
    }

    @Override
    protected void initData() {
        ArrayList<FamilyRoleBean.ResultBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new FamilyRoleBean.ResultBean());
        }
        roleAdapter = new RoleAdapter(R.layout.item_role, list);
        rolesRecyclerView.setAdapter(roleAdapter);
        roleAdapter.setOnItemClickListener(this);

    }
}
