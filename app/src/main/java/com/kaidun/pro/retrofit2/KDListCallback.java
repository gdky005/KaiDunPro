package com.kaidun.pro.retrofit2;


import com.kaidun.pro.bean.KDListBaseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ZKCallback
 * Created by WangQing on 2017/10/28.
 */

public abstract class KDListCallback<T> implements Callback<KDListBaseBean<T>> {
    @Override
    public void onResponse(Call<KDListBaseBean<T>> call, Response<KDListBaseBean<T>> response) {
        KDListBaseBean<T> baseBean = response.body();

        if (baseBean != null) {
            List<T> results = baseBean.getResult();
            if (baseBean.getStatusCode() == 100) {
                onResponse(baseBean, results);
            } else {
                onFailure(new Throwable(baseBean.getMessage()));
            }

        } else {
            onFailure(new Throwable("baseBean is null!"));
        }
    }

    @Override
    public void onFailure(Call<KDListBaseBean<T>> call, Throwable t) {
        onFailure(t);
    }

    public abstract void onResponse(KDListBaseBean<T> baseBean, List<T> result);

    public abstract void onFailure(Throwable throwable);
}
