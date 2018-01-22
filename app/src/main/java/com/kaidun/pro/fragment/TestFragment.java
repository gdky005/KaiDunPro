package com.kaidun.pro.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kaidun.pro.R;

import team.zhuoke.sdk.base.BaseFragment;

/**
 * 测试页面
 * Created by WangQing on 2018/1/22.
 */

public class TestFragment extends BaseFragment {

    public static final String KEY = "key";

    TextView textView;


    public static TestFragment newInstance(int navType) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, navType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.textView);
    }

    @Override
    public void initData(Bundle bundle1) {
        Bundle bundle = getArguments();

        int newKey = bundle.getInt(KEY, -1);

        textView.setText("" + newKey);

    }

    @Override
    public void initListener() {

    }
}
