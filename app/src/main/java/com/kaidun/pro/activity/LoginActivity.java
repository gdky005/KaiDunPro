package com.kaidun.pro.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaidun.pro.MainActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.AccountAdapter;
import com.kaidun.pro.bean.AccountData;
import com.kaidun.pro.bean.AreaBean;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.chooserole.ChooseRoleActivity;
import com.kaidun.pro.kd.KaiDunSP;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;
import com.kaidun.pro.utils.LoadingUtils;
import com.kaidun.pro.views.RecDividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * Created by Doraemon on 2018/1/23.
 */

public class LoginActivity extends KDBaseActivity implements AdapterView.OnItemSelectedListener {

    /**
     * 添加 app 需要的动态权限
     */
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE};

    @BindView(R.id.spinner_addr)
    Spinner addrSpinner;
    @BindView(R.id.et_login_account)
    EditText accountEt;
    @BindView(R.id.et_login_pwd)
    EditText pwdEt;
    List<AreaBean> areaBeanList;
    List<String> areaNameList;
    private String areaCode;
    AccountAdapter accountDataAdapter;
    ArrayAdapter arrayAdapter;
    KaiDunSP kaiDunSP;
    List<AccountData> accountDataList;
    @BindView(R.id.ll_choose_account)
    LinearLayout chooseAccountLay;
    @BindView(R.id.tv_clear)
    TextView clearTv;
    @BindView(R.id.rv_account_list)
    ZKRecycleView accountRv;
    @BindView(R.id.rl_login_root)
    RelativeLayout rootLay;


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
        addrSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        areaCode = areaBeanList.get(position).getAreaCode();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.btn_login, R.id.rl_login_root, R.id.et_login_account,
            R.id.tv_clear, R.id.iv_reset_account})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String account = accountEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                if (checkIsValid(account, pwd, areaCode)) {
                    login(account, pwd, areaCode);
                }
                break;
            case R.id.rl_login_root:
                if (chooseAccountLay.isShown()) {
                    chooseAccountLay.setVisibility(View.GONE);
                }
                break;
            case R.id.et_login_account:
                if (!chooseAccountLay.isShown() && accountDataList.size() > 0) {
                    chooseAccountLay.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_clear:
                accountEt.setText("");
                pwdEt.setText("");
                chooseAccountLay.setVisibility(View.GONE);
                accountDataList.clear();
                kaiDunSP.put(KaiDunSP.KEY_USERCODE_AND_PWD, "");
                break;
            case R.id.iv_reset_account:
                accountEt.setText("");
                pwdEt.setText("");
                break;

        }
    }

    @Override
    protected void initData() {
        areaBeanList = new ArrayList<>();
        areaNameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, areaNameList);
        kaiDunSP = new KaiDunSP();
        getLocalUserCode();
        getAreaList();
        verifyStoragePermissions(this);
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
        KDAccountManager kdAccountManager = KDAccountManager.getInstance();
        kdAccountManager.setLoginFinish(login -> {
            // TODO: 2018/1/25  这里请处理你的逻辑
            LoadingUtils.dismiss();
            if (login != null) {
                String key = "NIIGnE5O0ZU9BtSqREDlEwWo";
                PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, key);

                //存储登录账户
                boolean isExist = false;
                for (int i = 0; i < accountDataList.size(); i++) {
                    if (TextUtils.equals(account, accountDataList.get(i).getUserCode())) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    AccountData accountData = new AccountData(account, pwd);
                    accountDataList.add(0, accountData);
                    setDataList(KaiDunSP.KEY_USERCODE_AND_PWD, accountDataList);
                }
                chargeSelectRole(account, areaCode);
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

    public void verifyStoragePermissions(Activity activity) {
        PermissionUtils.requestPermissions(activity, 200, PERMISSIONS_STORAGE, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(String[] deniedPermissions) {
                ToastUtils.showShort("您禁用了以下权限，app 可能无法正常运行：\n" + deniedPermissions.toString());

            }
        });
    }

    private void getLocalUserCode() {
        accountDataList = getDataList(KaiDunSP.KEY_USERCODE_AND_PWD);
        if (accountDataList.size() > 0) {
            accountDataAdapter = new AccountAdapter(accountDataList);
            accountRv.setAdapter(accountDataAdapter);
            RecDividerItemDecoration decoration = new RecDividerItemDecoration(
                    getResources().getColor(R.color.color_fca948), 2, false);
            //divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
            accountRv.addItemDecoration(decoration);
            accountDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    accountEt.setText(accountDataList.get(position).getUserCode());
                    pwdEt.setText(accountDataList.get(position).getPwd());
                    accountEt.setSelection(accountEt.getText().length());
                    chooseAccountLay.setVisibility(View.GONE);
                }
            });
        }
    }

    /**
     * 获取本地账号信息
     *
     * @param tag
     * @return
     */
    public List<AccountData> getDataList(String tag) {
        List<AccountData> datalist = new ArrayList<AccountData>();
        String jsonData = (String) kaiDunSP.get(tag, "");
        if (TextUtils.isEmpty(jsonData)) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(jsonData, new TypeToken<List<AccountData>>() {
        }.getType());
        return datalist;

    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String jsonData = gson.toJson(datalist);
        KaiDunSP kaiDunSP = new KaiDunSP();
        kaiDunSP.put(tag, jsonData);

    }

}
