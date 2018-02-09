package com.kaidun.pro.notebook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kaidun.pro.R;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.fragment.PicFragment;

/**
 * @author Yunr
 * @date 2018/02/02 15:03
 */
public class PicActivity extends KDBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic;
    }

    @Override
    protected void initViews() {
        mTitle.setText("照片");
        String ccId = getIntent().getStringExtra("ccId");
        String classId = getIntent().getStringExtra("classId");
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.content_fragment, PicFragment.newInstance(ccId, classId));
        transaction.commitNow();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {

    }

}
