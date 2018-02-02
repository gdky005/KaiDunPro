package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.Constant;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.PicAdapter;
import com.kaidun.pro.bean.KDListBaseBean;
import com.kaidun.pro.bean.PicBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDListCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.zhuoke.sdk.base.BaseFragment;
import team.zhuoke.sdk.component.ZKRecycleView;
import team.zhuoke.sdk.utils.L;

/**
 * PicFragment
 * Created by WangQing on 2018/1/24.
 */

public class PicFragment extends BaseFragment implements EasyRefreshLayout.EasyEvent {
    @BindView(R.id.pic_recycle_view)
    ZKRecycleView picRecycleView;
    Unbinder unbinder;
    private EasyRefreshLayout mNoteBookRefresh;

    private List<PicBean> list;
    private PicAdapter picAdapter;
    private String ccId;
    private String classId;

    public static PicFragment newInstance() {
        PicFragment fragment = new PicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static PicFragment newInstance(String ccId, String classId) {
        PicFragment fragment = new PicFragment();
        Bundle args = new Bundle();
        args.putString(Constant.CC_ID, ccId);
        args.putString(Constant.CLASS_ID, classId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    public void initView(View view) {
        initRecyclerView();

        mNoteBookRefresh = view.findViewById(R.id.pic_refresh);
        mNoteBookRefresh.setEnablePullToRefresh(true);
        mNoteBookRefresh.addEasyEvent(this);
        mNoteBookRefresh.setLoadMoreModel(LoadModel.NONE);
        mNoteBookRefresh.setHideLoadViewAnimatorDuration(1000);
        mNoteBookRefresh.setHideLoadViewAnimatorDuration(1000);

    }

    private void initRecyclerView() {
        list = new ArrayList<>();

//        for (int i = 0; i < 60; i++) {
//            PicBean itemBean = new PicBean();
//            itemBean.setUploadTime("2018年01月24日 " + i);
//            list.add(itemBean);
//        }
        picAdapter = new PicAdapter(list);

        picRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        picRecycleView.setAdapter(picAdapter);
    }

    @Override
    public void initData(Bundle bundle) {
        Bundle data = getArguments();
        if (data != null && data.getString(Constant.CC_ID) != null
                && data.getString(Constant.CLASS_ID) != null) {
            ccId = data.getString(Constant.CC_ID);
            classId = data.getString(Constant.CLASS_ID);
        }

        refreshData();
    }

    private void refreshData() {
        JSONObject jsonObject = new JSONObject();
        if (!TextUtils.isEmpty(ccId) && !TextUtils.isEmpty(classId)) {
            try {
                jsonObject.put("ccId", ccId);
                jsonObject.put("classId", classId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        KDConnectionManager.getInstance().getZHApi().selectFamilyPicture(
                KDRequestUtils.getRequestBody(jsonObject)).enqueue(new KDListCallback<PicBean>() {
            @Override
            public void onResponse(KDListBaseBean<PicBean> baseBean, List<PicBean> result) {
                picAdapter.setNewData(result);
                if (mNoteBookRefresh != null) {
                    mNoteBookRefresh.refreshComplete();
                }
                L.d("selectFamilyPicture: " + result.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (mNoteBookRefresh != null) {
                    mNoteBookRefresh.refreshComplete();
                }
                ToastUtils.showShort(throwable.getMessage());
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefreshing() {
        refreshData();
    }
}
