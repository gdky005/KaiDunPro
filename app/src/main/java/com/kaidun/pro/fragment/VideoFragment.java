package com.kaidun.pro.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.adapter.VideoFragmentAdapter;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.VideoBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    private TabLayout tabPackage;
    private ViewPager vpVideo;
    public static final String KEY = "key";
    private List<VideoBean> list = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView(View view) {
        tabPackage = view.findViewById(R.id.tab_package);
        vpVideo = view.findViewById(R.id.vp_video);

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
        KDConnectionManager.getInstance().getZHApi()
                .getAllVideo(KDRequestUtils.getRequestBody())
                .enqueue(new KDCallback<List<VideoBean>>() {
                    @Override
                    public void onResponse(KDBaseBean<List<VideoBean>> baseBean, List<VideoBean> result) {

                        if (baseBean.getStatusCode() == 100) {
                            list.clear();
                            list.addAll(result);
                            List<Fragment> fragments = new ArrayList<>(list.size());
                            String[] titles = new String[list.size()];
                            for (int i = 0; i < list.size(); i++) {
                                VideoBean videoBean = list.get(i);
                                SubVideoFragment sub = new SubVideoFragment();
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("sub", videoBean);
                                sub.setArguments(bundle);
                                fragments.add(sub);
                                titles[i] = videoBean.getCourseSortName();
                            }
                            VideoFragmentAdapter pagerAdapter = new VideoFragmentAdapter(getChildFragmentManager(),
                                    fragments);
                            vpVideo.setAdapter(pagerAdapter);
                            pagerAdapter.setTitles(titles);
                            tabPackage.setupWithViewPager(vpVideo);

                        } else
                            ToastUtils.showShort(baseBean.getMessage());

                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });
    }

    @Override
    public void initListener() {

    }


}
