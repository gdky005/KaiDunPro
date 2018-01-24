package com.kaidun.pro.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.chooserole.ChooseRoleActivity;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.utils.L;

/**
 * Created by Doraemon on 2018/1/23.
 */

public class LoginActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spinner_addr)
    Spinner addrSpinner;
    @BindView(R.id.et_login_account)
    EditText accountEt;
    @BindView(R.id.et_login_pwd)
    EditText pwdEt;
    List<String> areaCodeList;
    private String areaCode;


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
        areaCode = areaCodeList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.btn_login})
    public void onViewClick(View view) {
        String account = accountEt.getText().toString();
        String pwd = pwdEt.getText().toString();
//        if (checkIsValid(account, pwd, areaCode)) {
//            login(account, pwd, areaCode);
//        }
        login("10007027", "10007027", "1001");

    }

    @Override
    protected void initData() {
        areaCodeList = new ArrayList<>();
        List<String> areaList = new ArrayList<>();
        areaList.add("上海");
        areaCodeList.add("1001");
        areaList.add("北京");
        areaCodeList.add("1002");
        areaList.add("广州");
        areaCodeList.add("1003");
        areaList.add("深圳");
        areaCodeList.add("1004");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, areaList);
        addrSpinner.setAdapter(arrayAdapter);

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

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", account);
            jsonObject.put("passWord", pwd);
            jsonObject.put("areaCode", areaCode);
            jsonObject.put("loginType", "003");

            KDConnectionManager.getInstance().getZHApi().login(KDRequestUtils.getHeaderMaps(), KDRequestUtils.getRequestBody(jsonObject)).enqueue(new KDCallback<LoginBean>() {
                @Override
                public void onResponse(KDBaseBean<LoginBean> baseBean, LoginBean result) {
                    if (baseBean.getStatusCode() == 100) {//登陆成功标识？
                        if (result != null) {
                            LoginBean.DataBean dataBean = result.getData();
                            if (dataBean != null) {
                                KDAccountManager.getInstance().setUserInfoBean(dataBean);
                                //TODO 账户存本地 下次自动登陆？
                                KDAccountManager.getInstance().setToken(result.getToken());
                                ChooseRoleActivity.start(LoginActivity.this);
                                finish();
                            }
                            L.d("onResponse: " + result.toString());
                        }
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
}
