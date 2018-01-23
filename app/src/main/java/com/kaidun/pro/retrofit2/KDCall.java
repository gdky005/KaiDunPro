package com.kaidun.pro.retrofit2;

import com.kaidun.pro.bean.KDBaseBean;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ZHCall
 * Created by WangQing on 2017/10/28.
 */
public interface KDCall<K> extends Call<KDBaseBean<K>> {
    @Override
    Response<KDBaseBean<K>> execute() throws IOException;

    @Override
    void enqueue(Callback<KDBaseBean<K>> callback);

    @Override
    boolean isExecuted();

    @Override
    void cancel();

    @Override
    boolean isCanceled();

    @Override
    Call<KDBaseBean<K>> clone();

    @Override
    Request request();
}
