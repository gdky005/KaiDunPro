package com.kaidun.pro;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

import team.zhuoke.sdk.ZKBase;

/**
 * KaiDunApplication
 * Created by WangQing on 2018/1/22.
 */
public class KaiDunApplication extends Application {

    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        Fresco.initialize(mContext);
        ZKBase.init(this, BuildConfig.DEBUG);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // android 7.0系统解决拍照的问题
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

    /**
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
