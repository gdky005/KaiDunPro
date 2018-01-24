package com.kaidun.pro;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.fragment.TestFragment;
import com.kaidun.pro.fragment.VideoFragment;
import com.kaidun.pro.home.HomeFragment;
import com.kaidun.pro.notebook.NoteBookFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static final int NAV_TYPE_MAIN = 0;
    public static final int NAV_TYPE_VIDEO = 1;
    public static final int NAV_TYPE_PICTURE = 2;
    public static final int NAV_TYPE_PARENT_NOTEBOOK = 3;
    public static final int NAV_TYPE_MESSAGE = 4;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.container_view_pager)
    ViewPager containerViewPager;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ConstraintLayout container;


    private static Fragment[] TABLE_FRAGMENT = new Fragment[]{
            HomeFragment.newInstance(),
            VideoFragment.newInstance(NAV_TYPE_VIDEO),
            TestFragment.newInstance(NAV_TYPE_PICTURE),
            NoteBookFragment.newInstance(NAV_TYPE_PARENT_NOTEBOOK),
            TestFragment.newInstance(NAV_TYPE_MESSAGE)};

    public static int[] NAV_TYPE = new int[]{NAV_TYPE_MAIN, NAV_TYPE_VIDEO, NAV_TYPE_PICTURE, NAV_TYPE_PARENT_NOTEBOOK, NAV_TYPE_MESSAGE};

    /**
     * 添加 app 需要的动态权限
     */
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE};

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int itemId = R.id.navigation_main;
            switch (position) {
                case NAV_TYPE_MAIN:
                    itemId = R.id.navigation_main;
                    break;
                case NAV_TYPE_VIDEO:
                    itemId = R.id.navigation_video;
                    break;
                case NAV_TYPE_PICTURE:
                    itemId = R.id.navigation_picture;
                    break;
                case NAV_TYPE_PARENT_NOTEBOOK:
                    itemId = R.id.navigation_parent_notebook;
                    break;
                case NAV_TYPE_MESSAGE:
                    itemId = R.id.navigation_message;
                    break;
            }

            navigation.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    message.setText(R.string.nav_title_main);
                    containerViewPager.setCurrentItem(NAV_TYPE_MAIN);
                    return true;
                case R.id.navigation_video:
                    message.setText(R.string.nav_title_video);
                    containerViewPager.setCurrentItem(NAV_TYPE_VIDEO);
                    return true;
                case R.id.navigation_picture:
                    message.setText(R.string.nav_title_picture);
                    containerViewPager.setCurrentItem(NAV_TYPE_PICTURE);
                    return true;
                case R.id.navigation_parent_notebook:
                    containerViewPager.setCurrentItem(NAV_TYPE_PARENT_NOTEBOOK);
                    message.setText(R.string.nav_title_parent_notebook);
                    return true;
                case R.id.navigation_message:
                    containerViewPager.setCurrentItem(NAV_TYPE_MESSAGE);
                    message.setText(R.string.nav_title_message);
                    return true;
            }
            return false;
        }
    };

    /**
     * MainActivity 中的四大底标签页面
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        public static final int NAV_TYPE_MAIN = 0;
        public static final int NAV_TYPE_VIDEO = 1;
        public static final int NAV_TYPE_PICTURE = 2;
        public static final int NAV_TYPE_PARENT_NOTEBOOK = 3;
        public static final int NAV_TYPE_MESSAGE = 4;


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case NAV_TYPE_MAIN:
                    return TABLE_FRAGMENT[0];
                case NAV_TYPE_VIDEO:
                    return TABLE_FRAGMENT[1];
                case NAV_TYPE_PICTURE:
                    return TABLE_FRAGMENT[2];
                case NAV_TYPE_PARENT_NOTEBOOK:
                    return TABLE_FRAGMENT[3];
                case NAV_TYPE_MESSAGE:
                    return TABLE_FRAGMENT[4];
            }

            return null;
        }

        @Override
        public int getCount() {
            return NAV_TYPE.length;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        containerViewPager.setOffscreenPageLimit(2);
        containerViewPager.addOnPageChangeListener(onPageChangeListener);
        containerViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initData() {

        verifyStoragePermissions(this);
    }

    public void verifyStoragePermissions(Activity activity) {
        PermissionUtils.requestPermissions(activity, 200, PERMISSIONS_STORAGE, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(String[] deniedPermissions) {
                ToastUtils.showShort("您禁用了以下权限，app 可能无法正常运行：\n" + deniedPermissions.toString());

            }
        });
    }
}
