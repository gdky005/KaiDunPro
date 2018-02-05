package com.kaidun.pro.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kaidun.pro.R;
import com.kaidun.pro.adapter.RvListener;
import com.kaidun.pro.adapter.SubVideoAdapter;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.SubVideoBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VideoDetailActivity extends KDBaseActivity implements View.OnClickListener {

    private RecyclerView rvSubVideo;
    private SubVideoAdapter adapter;
    private ImageView ivBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initViews() {
        rvSubVideo = findViewById(R.id.rv_subVideo);
        ivBack = findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String code = intent.getStringExtra("code");
        try {
            getVideoDetail(id, code);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getVideoDetail(String id, String code) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSortId", id);
        jsonObject.put("bookCode", code);
        KDConnectionManager.getInstance().getZHApi()
                .getAllSubVideo(KDRequestUtils.getRequestBody(jsonObject))
                .enqueue(new KDCallback<List<SubVideoBean>>() {
                    @Override
                    public void onResponse(KDBaseBean<List<SubVideoBean>> baseBean, List<SubVideoBean> result) {

                        if (baseBean.getStatusCode() == 100) {

                            SubVideoBean subVideoBean = new SubVideoBean();
                            result.add(0, subVideoBean);
                            adapter = new SubVideoAdapter(mContext, result, new RvListener() {
                                @Override
                                public void onItemClick(int id, int position) {
                                    //TODO 点击事件

                                    if (position != 0) {
                                        Intent intent = new Intent();
                                        intent.setClass(mContext, VideoPlayActivity.class);
                                        intent.putExtra("url", result.get(position).getThumbnallUrl());
                                        intent.putExtra("videoUrl", result.get(position).getDvdUrl());
                                        intent.putExtra("name", result.get(position).getTageTitle());
                                        startActivity(intent);
                                    }


                                }
                            });
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 12);
                            GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    if (position == 0)
                                        return 12;
                                    else if (position == 1 || position == 2 || position == 3)
                                        return 4;
                                    else
                                        return 3;
                                }
                            };
                            gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
                            rvSubVideo.setLayoutManager(gridLayoutManager);
                            rvSubVideo.setAdapter(adapter);

                        } else
                            KDUtils.showErrorToast();

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        KDUtils.showErrorToast();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (ivBack == v)
            finish();
    }
}
