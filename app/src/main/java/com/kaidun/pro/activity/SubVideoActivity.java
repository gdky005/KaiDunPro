package com.kaidun.pro.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.RvListener;
import com.kaidun.pro.adapter.SubVideoAdapter;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.SubVideoBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import team.zhuoke.sdk.base.BaseActivity;

public class SubVideoActivity extends BaseActivity {

    private RecyclerView rvSubVideo;
    private SubVideoAdapter adapter;
    private List<SubVideoBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_video;
    }

    @Override
    protected void initViews() {
        setTitle("二级视频列表");
        rvSubVideo = findViewById(R.id.rv_subVideo);
    }

    @Override
    protected void initListener() {

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
        jsonObject.put("userCode", "10007027");
        jsonObject.put("areaCode", "1001");
        jsonObject.put("courseSortId", id);
        jsonObject.put("bookCode", code);
        KDConnectionManager.getInstance().getZHApi()
                .getAllSubVideo(KDRequestUtils.getHeaderMaps(), KDRequestUtils.getRequestBody(jsonObject))
                .enqueue(new KDCallback<List<SubVideoBean>>() {
                    @Override
                    public void onResponse(KDBaseBean<List<SubVideoBean>> baseBean, List<SubVideoBean> result) {

                        if (baseBean.getStatusCode() == 100) {

                            adapter = new SubVideoAdapter(mContext, result, new RvListener() {
                                @Override
                                public void onItemClick(int id, int position) {
                                    //TODO 点击事件
                                    Intent intent = new Intent();
                                    intent.setClass(mContext, VideoPlayActivity.class);
                                    intent.putExtra("url", result.get(position).getThumbnallUrl());
                                    intent.putExtra("name", result.get(position).getTageTitle());
                                    startActivity(intent);

                                }
                            });
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
                            rvSubVideo.setLayoutManager(gridLayoutManager);
                            rvSubVideo.setAdapter(adapter);

                        } else
                            ToastUtils.showShort(baseBean.getMessage());

                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });
    }
}
