package com.kaidun.pro;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

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

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", "10007027");
            jsonObject.put("passWord", "10007027");
            jsonObject.put("areaCode", "1001");
            jsonObject.put("loginType", "003");

            kdApi.login(KDRequestUtils.getHeaderMaps(), KDRequestUtils.getRequestBody(jsonObject)).enqueue(new KDCallback<LoginBean>() {
                        @Override
                        public void onResponse(KDBaseBean<LoginBean> baseBean, LoginBean result) {

                            Log.d(TAG, "onResponse: " + result.toString());

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

}
