package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.home.adapter.NotificationAdapter;
import com.kaidun.pro.home.bean.Notification;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class NotificationFragment extends BaseFragment {
    @BindView(R.id.rv_show_notification)
    RecyclerView mShowNotification;
    private List<Notification> mNotifications = new ArrayList<>();
    private NotificationAdapter mAdapter;
    @BindView(R.id.pb_loading)
    ProgressBar mLoading;

    public static NotificationFragment newInstance() {

        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mShowNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NotificationAdapter(R.layout.item_notification, mNotifications);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        mShowNotification.setAdapter(mAdapter);
        mShowNotification.addItemDecoration(divider);
    }

    @Override
    public void initData(Bundle bundle) {
        Notification notification = new Notification("2017/12/26",
                "凯顿幼儿美语推出新课程了，欢迎各位家长带孩子来体验，活动免费开放四天。",
                "推荐消息");
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mNotifications.add(notification);
        mAdapter.notifyDataSetChanged();
        try {
            mLoading.setVisibility(View.VISIBLE);
            getNotification();
        } catch (JSONException e) {
            mLoading.setVisibility(View.GONE);
        }
    }

    private void getNotification() throws JSONException {
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
        kdApi.getPushMessage(KDRequestUtils.getHeaderMaps(), KDRequestUtils.getBaseInfo()).enqueue(new Callback<KDBaseBean>() {
            @Override
            public void onResponse(Call<KDBaseBean> call, Response<KDBaseBean> response) {
                if (response.body() != null) {
                    if (!(response.body().getStatusCode() == 100)) {
                        showToast(response.body().getMessage());
                    } else {
                        showToast("请求成功？");
                    }
                }
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<KDBaseBean> call, Throwable t) {
                mLoading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initListener() {

    }

    private void showToast(String msg) {
        ToastUtils.showShort(msg);
    }
}
