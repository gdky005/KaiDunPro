package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.home.adapter.RecommendedAdapter;
import com.kaidun.pro.home.bean.Recommended;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class RecommendedFragment extends BaseFragment {
    @BindView(R.id.rv_show_recommended)
    RecyclerView mShowRecommended;
    private RecommendedAdapter mAdapter;
    private List<Recommended.ResultBean> mRecommendeds = new ArrayList<>();
    @BindView(R.id.pb_loading)
    ProgressBar mLoading;

    public static RecommendedFragment newInstance() {

        Bundle args = new Bundle();

        RecommendedFragment fragment = new RecommendedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommended;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mShowRecommended.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecommendedAdapter(R.layout.item_recommended, mRecommendeds);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        mShowRecommended.setAdapter(mAdapter);
        mShowRecommended.addItemDecoration(divider);
    }

    @Override
    public void initData(Bundle bundle) {
        try {
            mLoading.setVisibility(View.VISIBLE);
            getRecommended();
        } catch (JSONException e) {
            mLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListener() {

    }

    private void getRecommended() throws JSONException {
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
        kdApi.getRecommend(KDRequestUtils.getRequestBody()).enqueue(new Callback<Recommended>() {
            @Override
            public void onResponse(Call<Recommended> call, Response<Recommended> response) {
                if (response.body() != null) {
                    if (!(response.body().getStatusCode() == 100)) {
                        showToast(response.body().getMessage());
                    } else {
                        mRecommendeds.clear();
                        mRecommendeds.addAll(response.body().getResult());
                        mAdapter.notifyDataSetChanged();
                    }
                }
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Recommended> call, Throwable t) {
                mLoading.setVisibility(View.GONE);
            }
        });
    }

    private void showToast(String msg) {
        ToastUtils.showShort(msg);
    }
}
