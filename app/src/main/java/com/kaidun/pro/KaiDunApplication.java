package com.kaidun.pro;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bugtags.library.Bugtags;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kaidun.pro.managers.KDConnectionManager;

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

        //在这里初始化
        try {
            Bugtags.start("fedc05f31e8d57fd6a20bb9d1fa7349c", this, Bugtags.BTGInvocationEventBubble);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ZKBase.init(this, BuildConfig.DEBUG);

        Stetho.initialize(Stetho.newInitializerBuilder(mContext)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(mContext)
                                .provide(new HprofDumperPlugin(mContext))
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(mContext))
                .build());

        //添加 Stetho 的拦截器
        KDConnectionManager.getInstance().getBuilder().addNetworkInterceptor(new StethoInterceptor());
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
