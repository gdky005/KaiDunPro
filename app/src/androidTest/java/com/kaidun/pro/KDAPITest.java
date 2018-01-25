package com.kaidun.pro;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * KDAPITest
 * Created by WangQing on 2018/1/23.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class KDAPITest {

    private static final String TAG = "KDAPITest";

    KDApi kdApi;
    Context context;
    CountDownLatch countDownLatch;

    @Before
    public void init() {
        countDownLatch = new CountDownLatch(1);
        context = InstrumentationRegistry.getTargetContext();
        kdApi = KDConnectionManager.getInstance().getZHApi();
    }

    @Test
    public void testLogin() {
//        测试账号有：10007027，10009010， 账号和密码都相同
//        8009030410
//        8009030324
//        8009034272
//        8009028864
//        8009028361
//        8009024777
//        8009011327
//        8009001100
//        10006568
//        10007027
//
//        密码和账号是一样的
//
//        areaCode都是1001

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", "10007027");
            jsonObject.put("passWord", "10007027");
            jsonObject.put("areaCode", "1001");
            jsonObject.put("loginType", "003");

            kdApi.login(KDRequestUtils.getLoginRequestBody(jsonObject)).enqueue(new KDCallback<LoginBean>() {
                @Override
                public void onResponse(KDBaseBean<LoginBean> baseBean, LoginBean result) {
                    if (result != null) {
                        String token = result.getToken();

                        Log.d(TAG, "onResponse: " + result.toString());
                    }


                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(Throwable throwable) {

                    Log.d(TAG, "onFailure: " + throwable.getMessage());
                    countDownLatch.countDown();

                    Assert.assertTrue(throwable.getMessage(), false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testSelectFamilyRole() {
//
//        // TODO: 2018/1/23  为了获取到 token
//        KDAccountManager.getInstance().defaultLogin();
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("userCode", "10007027");
//            jsonObject.put("areaCode", "1001");
//
//            kdApi.selectFamilyRole(KDRequestUtils.getHeaderMaps(), KDRequestUtils.getRequestBody(jsonObject)).enqueue(new Callback<FamilyRoleBean>() {
//                @Override
//                public void onResponse(Call<FamilyRoleBean> call, Response<FamilyRoleBean> response) {
//
//                    FamilyRoleBean familyRoleBean = response.body();
//
//                    if (familyRoleBean != null && familyRoleBean.getStatusCode() == 100) {
//                        Log.d(TAG, "onResponse: " + familyRoleBean.toString());
//                    }
//
//
//                    countDownLatch.countDown();
//                }
//
//                @Override
//                public void onFailure(Call<FamilyRoleBean> call, Throwable throwable) {
//                    Log.d(TAG, "onFailure: " + throwable.getMessage());
//                    countDownLatch.countDown();
//
//                    Assert.assertTrue(throwable.getMessage(), false);
//                }
//
//
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    public void testSelectFamContContext() {

        KDAccountManager.getInstance().defaultLogin();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ccId = "4B93B97398216E08E0531064410ABCF4";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ccId", ccId);
            KDConnectionManager.getInstance().getZHApi().sendFolwer(
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            Log.d("----", response.toString());
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
