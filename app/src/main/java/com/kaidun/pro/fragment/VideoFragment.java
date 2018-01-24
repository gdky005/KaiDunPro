package com.kaidun.pro.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.adapter.VideoFragmentAdapter;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.VideoBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    private TabLayout tabPackage;
    private ViewPager vpVideo;
    private TextView tvTitle;
    public static final String KEY = "key";


    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView(View view) {
        tabPackage = view.findViewById(R.id.tab_package);
        vpVideo = view.findViewById(R.id.vp_video);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("视频");
        VideoFragmentAdapter pagerAdapter = new VideoFragmentAdapter(getChildFragmentManager(),
                new Fragment[]{new SubVideoFragment(),
                        new SubVideoFragment(), new SubVideoFragment()});
        vpVideo.setAdapter(pagerAdapter);
        pagerAdapter.setTitles(new String[]{"ABC", "LA", "SC"});
        tabPackage.setupWithViewPager(vpVideo);

    }

    public static VideoFragment newInstance(int navType) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, navType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle bundle) {
        try {
            getVideo();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getVideo() throws JSONException {
        KDAccountManager.getInstance().defaultLogin();
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCode", "10007027");
        jsonObject.put("areaCode", "1001");
        kdApi.getAllVideo(KDRequestUtils.getHeaderMaps(),
                KDRequestUtils.getRequestBody(jsonObject)).enqueue(new Callback<VideoBean>() {

            @Override
            public void onResponse(Call<VideoBean> call, Response<VideoBean> response) {
                if (response.body() != null) {
                    Log.d("response--->ok", response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<VideoBean> call, Throwable t) {
                Log.d("response--->ok", t.getMessage());

            }
        });
    }

    @Override
    public void initListener() {

    }


}
