package com.kaidun.pro.chooserole;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaidun.pro.MainActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.chooserole.adapter.RoleAdapter;
import com.kaidun.pro.chooserole.bean.ChooseRoleBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class ChooseRoleActivity extends KDBaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_roles)
    RecyclerView rolesRecyclerView;
    private RoleAdapter roleAdapter;
    private String roleCode;
    ArrayList<ChooseRoleBean> roleList;
    private int curPos = -1;

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
        setTitle("请选择家长关系");
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (curPos != -1) {
            roleList.get(curPos).setSelected(false);
            adapter.notifyItemChanged(curPos);
        }
        roleList.get(position).setSelected(true);
        adapter.notifyItemChanged(position);

        roleCode = roleList.get(position).getFamilyRoleBean().getFamilyRoleCode();
        curPos = position;
    }

    @Override
    protected void initData() {
        getFamilyRoles();
        roleList = new ArrayList<>();
        roleAdapter = new RoleAdapter(R.layout.item_role, roleList);
        rolesRecyclerView.setAdapter(roleAdapter);
        roleAdapter.setOnItemClickListener(this);

    }

    @OnClick({R.id.btn_confirm})
    public void onViewClick(View view) {
        if (curPos != -1) {
            updateFamilyRoles(roleCode);
        } else {
            ToastUtils.showShort("请选择家长关系");

        }

    }

    private void getFamilyRoles() {

        try {
            KDConnectionManager.getInstance().getZHApi().selectFamilyRole(KDRequestUtils.getRequestBody()).
                    enqueue(new KDCallback<List<FamilyRoleBean>>() {
                        @Override
                        public void onResponse(KDBaseBean<List<FamilyRoleBean>> baseBean, List<FamilyRoleBean> response) {
                            if (baseBean.getStatusCode() == 100) {
                                for (int i = 0; i < response.size(); i++) {
                                    roleList.add(new ChooseRoleBean(response.get(i)));
                                }
                                roleAdapter.notifyDataSetChanged();
                            } else {
                                ToastUtils.showShort(baseBean.getMessage());
                            }

                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                        }


                    });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void updateFamilyRoles(String familyRoleCode) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("familyRoleCode", familyRoleCode);

            KDConnectionManager.getInstance().getZHApi().updateFamilyRole(KDRequestUtils.getRequestBody(jsonObject)).enqueue(
                    new KDCallback<String>() {
                        @Override
                        public void onResponse(KDBaseBean<String> baseBean, String result) {
                            if (baseBean.getStatusCode() == 100) {
                                startActivity(new Intent(ChooseRoleActivity.this, MainActivity.class));
                                finish();
                            } else {
                                ToastUtils.showShort(baseBean.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            KDUtils.showErrorToast();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
