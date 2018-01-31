package com.kaidun.pro.managers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaidun.pro.Constant;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.utils.KDRequestUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team.zhuoke.sdk.ZKBase;

/**
 * KDConnectionManager 的网络管理类
 */
public class KDConnectionManager {

    private static final String TAG = "KDConnectionManager";


    //http://blog.csdn.net/u014695188/article/details/52985514
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();


    private static KDConnectionManager instance = null;

    private KDConnectionManager() {
    }

    public static KDConnectionManager getInstance() {
        if (instance == null) {
            synchronized (KDConnectionManager.class) {
                KDConnectionManager temp = instance;
                if (temp == null) {
                    temp = new KDConnectionManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }


    private Gson getGson() {
        return new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    private Retrofit getRetrofit() {
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        if (ZKBase.isDebug()) {
            //log信息拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置Debug Log模式
            builder.addInterceptor(httpLoggingInterceptor);
        }

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();

                Map<String, String> map = KDRequestUtils.getHeaderMaps();
                for (Map.Entry<String, String> entry:
                        map.entrySet()) {
                    requestBuilder.header(entry.getKey(), entry.getValue());
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(Constant.KAIDUN_API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public KDApi getZHApi() {
        return getRetrofit().create(KDApi.class);
    }

    public void test() {
        Log.d(TAG, "test() called");
    }

}
