package pro.kaidun.com.kaidunpro.managers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pro.kaidun.com.kaidunpro.Constant;
import pro.kaidun.com.kaidunpro.api.KDApi;
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
