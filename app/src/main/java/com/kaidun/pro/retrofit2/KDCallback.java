package com.kaidun.pro.retrofit2;


import com.kaidun.pro.PageCtrl;
import com.kaidun.pro.bean.KDBaseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.ZKBase;

/**
 * ZKCallback
 * Created by WangQing on 2017/10/28.
 */

public abstract class KDCallback<T> implements Callback<KDBaseBean<T>> {
    @Override
    public void onResponse(Call<KDBaseBean<T>> call, Response<KDBaseBean<T>> response) {
        KDBaseBean<T> baseBean = response.body();

        if (baseBean != null) {
            T results = baseBean.getResult();

            int code = baseBean.getStatusCode();

//            if (code == 100) {
//                onResponse(baseBean, results);
//            } else
            if (code == 208) {
                PageCtrl.startLoginActivity(ZKBase.getContext());
            } else {
//                onFailure(new Throwable(baseBean.getMessage()));
                onResponse(baseBean, results);
            }

        } else {
            onFailure(new Throwable("baseBean is null!"));
        }
    }

    @Override
    public void onFailure(Call<KDBaseBean<T>> call, Throwable t) {
        onFailure(t);
    }

    public abstract void onResponse(KDBaseBean<T> baseBean, T result);

    public abstract void onFailure(Throwable throwable);
}
