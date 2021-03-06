package com.kaidun.pro.managers;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;

import org.json.JSONObject;

import team.zhuoke.sdk.utils.L;

/**
 * KDAccountManager
 * Created by WangQing on 2018/1/23.
 */

public class KDAccountManager {

    private static KDAccountManager instance = null;

    private KDAccountManager() {
    }

    public static KDAccountManager getInstance() {
        if (instance == null) {
            synchronized (KDAccountManager.class) {
                KDAccountManager temp = instance;
                if (temp == null) {
                    temp = new KDAccountManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }

    private String userCode;
    private String areaCode;

    private String passWord;
    private String loginType;

    private String token;
    private LoginBean.DataBean userInfoBean;
    private LoginFinish loginFinish;

    public void defaultLogin() {
        login("10007027", "10007027", "1001", "003");
    }

    /**
     * 登录账户
     *
     * @param userCode  用户账号
     * @param passWord  用户密码
     * @param areaCode  所选校区
     * @param loginType 登陆类型
     */
    public void login(String userCode, String passWord, String areaCode, String loginType) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", userCode);
            jsonObject.put("passWord", passWord);
            jsonObject.put("areaCode", areaCode);
            jsonObject.put("loginType", loginType);

            KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
            kdApi.login(KDRequestUtils.getLoginRequestBody(jsonObject)).enqueue(new KDCallback<LoginBean>() {
                @Override
                public void onResponse(KDBaseBean<LoginBean> baseBean, LoginBean result) {
                    if (baseBean.getStatusCode() == 100) {
                        if (result != null) {
                            LoginBean.DataBean dataBean = result.getData();
                            setPassWord(passWord);
                            setLoginType(loginType);

                            if (dataBean != null) {
                                setUserInfoBean(dataBean);
                            }

                            setToken(result.getToken());

                            if (loginFinish != null) {
                                loginFinish.loginFinish(result);
                            }

                            L.d("onResponse: " + result.toString());
                        }
                    } else {
                        ToastUtils.showShort(baseBean.getMessage());
                        if (loginFinish != null) {
                            loginFinish.loginFinish(null);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                    KDUtils.showErrorToast();
                    L.d("onFailure: " + throwable.getMessage());

                    if (loginFinish != null) {
                        loginFinish.loginFinish(null);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginBean.DataBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(LoginBean.DataBean userInfoBean) {
        this.userInfoBean = userInfoBean;

        setUserCode(userInfoBean.getUserCode());
        setAreaCode(userInfoBean.getAreaCode());
    }

    public void setLoginFinish(LoginFinish loginFinish) {
        this.loginFinish = loginFinish;
    }

    public interface LoginFinish {

        void loginFinish(LoginBean login);
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
