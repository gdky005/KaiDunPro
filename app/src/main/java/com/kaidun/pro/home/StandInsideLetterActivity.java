package com.kaidun.pro.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kaidun.pro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/22.
 */

public class StandInsideLetterActivity extends BaseActivity {
    @BindView(R.id.vp_inside_letter)
    ViewPager mInsideLetter;
    @BindView(R.id.tl_letter_type_tab)
    TabLayout mLetterTypeTab;
    private String[] mLetterTypeTabTitle = new String[2];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stand_inside_letter;
    }

    @Override
    protected void initViews() {
        mLetterTypeTabTitle[0] = "通知";
        mLetterTypeTabTitle[1] = "推荐";
        ButterKnife.bind(this);
        mInsideLetter.setAdapter(new InsideLetterAdapter(getSupportFragmentManager()));
        mLetterTypeTab.setupWithViewPager(mInsideLetter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    class InsideLetterAdapter extends FragmentPagerAdapter {
        private Fragment[] mFragments = new Fragment[2];

        public InsideLetterAdapter(FragmentManager fm) {
            super(fm);
            mFragments[0] = NotificationFragment.newInstance();
            mFragments[1] = RecommendedFragment.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mLetterTypeTabTitle[position];
        }
    }
}
