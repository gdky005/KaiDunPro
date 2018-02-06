package com.kaidun.pro.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.MainActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.AreaBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.chooserole.ChooseRoleActivity;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;
import com.kaidun.pro.utils.LoadingUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.ZKBase;

/**
 * Created by Doraemon on 2018/1/23.
 */

public class LoginActivity extends KDBaseActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spinner_addr)
    Spinner addrSpinner;
    @BindView(R.id.et_login_account)
    EditText accountEt;
    @BindView(R.id.et_login_pwd)
    EditText pwdEt;
    List<AreaBean> areaBeanList;
    List<String> areaNameList;
    @BindView(R.id.spinner_test_account)
    Spinner spinnerTestAccount;
    private String areaCode;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        testAccount();


    }

    /**
     * 添加多个测试账号
     */
    private void testAccount() {
        // TODO: 2018/1/25  这里是测试的，记得删除了
        if (ZKBase.isDebug()) {
            spinnerTestAccount.setVisibility(View.VISIBLE);
            List<String> areaList = new ArrayList<>();
            areaList.add("10009010");
            areaList.add("10047341");
            areaList.add("7007342");
            areaList.add("10007027");
            areaList.add("8009030410");
            areaList.add("8009030324");
            areaList.add("8009034272");
            areaList.add("8009028864");
            areaList.add("8009028361");
            areaList.add("8009024777");
            areaList.add("8009011327");
            areaList.add("8009001100");
            areaList.add("10006568");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this, R.layout.support_simple_spinner_dropdown_item, areaList);
            spinnerTestAccount.setAdapter(arrayAdapter);
            spinnerTestAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    accountEt.setText(areaList.get(position));
                    pwdEt.setText(areaList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            accountEt.setText(areaList.get(0));
            pwdEt.setText(areaList.get(0));
        }
    }

    @Override
    protected void initListener() {
        addrSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        areaCode = areaBeanList.get(position).getAreaCode();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.btn_login})
    public void onViewClick(View view) {
        // TODO: 2018/1/25 记得处理这里
        switch (view.getId()) {
            case R.id.btn_login:
                String account = accountEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                if (checkIsValid(account, pwd, areaCode)) {
                    login(account, pwd, areaCode);
                }
                break;

        }
    }

    @Override
    protected void initData() {
        areaBeanList = new ArrayList<>();
        areaNameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, areaNameList);
        getAreaList();
    }

    private boolean checkIsValid(String account, String pwd, String areaCode) {
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showShort("请输入账户");
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showShort("请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(areaCode)) {
            ToastUtils.showShort("请选择校区");
            return false;
        }
        return true;
    }

    public void login(String account, String pwd, String areaCode) {
//        测试账号有：10007027，10009010， 账号和密码都相同
        KDAccountManager kdAccountManager = KDAccountManager.getInstance();
        kdAccountManager.setLoginFinish(login -> {
            // TODO: 2018/1/25  这里请处理你的逻辑
            LoadingUtils.dismiss();
            if (login != null) {
                //TODO 替换正式api key
//                PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "NIIGnE5O0ZU9BtSqREDlEwWo");
                PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "BNnZg5IkOjn0V6Gu8R19fMss");

                //无退出登录 ，判断本地数据，切换账号会有问题
//                KaiDunSP kaiDunSP = new KaiDunSP();
//                boolean isFirstChooseRole = (boolean) kaiDunSP.get(KaiDunSP.KEY_TEST_ROLES, true);
//                if (!isFirstChooseRole) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                } else {
                chargeSelectRole(account, areaCode);
//                }
            }
        });
        LoadingUtils.show(this);
        kdAccountManager.login(account, pwd, areaCode, "003");
    }

    private void getAreaList() {
        try {
            KDConnectionManager.getInstance().getZHApi().getAreaList(KDRequestUtils.getRequestBody()).
                    enqueue(new KDCallback<List<AreaBean>>() {
                        @Override
                        public void onResponse(KDBaseBean<List<AreaBean>> baseBean, List<AreaBean> response) {
                            if (baseBean.getStatusCode() == 100) {
                                areaBeanList = response;
                                for (int i = 0; i < response.size(); i++) {
                                    areaNameList.add(response.get(i).getAreaName());
                                }
                                addrSpinner.setAdapter(arrayAdapter);

                            } else {
                                KDUtils.showErrorToast();
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

    /**
     * 设备是否绑定了身份
     *
     * @param userCode
     * @param areaCode
     */
    private void chargeSelectRole(String userCode, String areaCode) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("areaCode", areaCode);
            jsonObject.put("userCode", userCode);

            KDConnectionManager.getInstance().getZHApi().selectRoleByMachine(KDRequestUtils.getRequestBody(jsonObject)).enqueue(
                    new KDCallback<String>() {
                        @Override
                        public void onResponse(KDBaseBean<String> baseBean, String result) {
                            if (baseBean.getStatusCode() == 100) {
                                if (TextUtils.equals("002", result)) {
                                    ChooseRoleActivity.start(LoginActivity.this);
                                } else {
//                                    KaiDunSP kaiDunSP = new KaiDunSP();
//                                    kaiDunSP.put(KaiDunSP.KEY_TEST_ROLES, false);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
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
