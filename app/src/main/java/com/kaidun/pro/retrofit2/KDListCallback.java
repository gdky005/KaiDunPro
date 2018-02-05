package com.kaidun.pro.retrofit2;


import com.kaidun.pro.PageCtrl;
import com.kaidun.pro.bean.KDListBaseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.ZKBase;

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

            int code = baseBean.getStatusCode();

            if (code == 100) {
                onResponse(baseBean, results);
            } else if (code == 208) {
                PageCtrl.startLoginActivity(ZKBase.getContext());
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
