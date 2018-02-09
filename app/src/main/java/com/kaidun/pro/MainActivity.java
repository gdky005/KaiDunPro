package com.kaidun.pro;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.fragment.MsgFragment;
import com.kaidun.pro.fragment.PicFragment;
import com.kaidun.pro.fragment.VideoFragment;
import com.kaidun.pro.home.HomeFragment;
import com.kaidun.pro.notebook.NoteBookFragment;
import com.kaidun.pro.views.CustomRadioButton;

public class MainActivity extends KDBaseActivity {

    public static final String FLAG_PUSH_KEY = "FLAG_PUSH_KEY";

    private Fragment currentFragment;
    private RadioGroup radioGroup;
    private CustomRadioButton rbHome;
    private CustomRadioButton rbVideo;
    private CustomRadioButton rbPic;
    private CustomRadioButton rbBook;
    private CustomRadioButton rbMessage;

    public static final int NAV_TYPE_MAIN = 0;
    public static final int NAV_TYPE_VIDEO = 1;
    public static final int NAV_TYPE_PICTURE = 2;
    public static final int NAV_TYPE_PARENT_NOTEBOOK = 3;
    public static final int NAV_TYPE_MESSAGE = 4;


    private static Fragment[] fragmentArray = new Fragment[]{
            HomeFragment.newInstance(),
            VideoFragment.newInstance(NAV_TYPE_VIDEO),
            PicFragment.newInstance(),
            NoteBookFragment.newInstance(NAV_TYPE_PARENT_NOTEBOOK),
            MsgFragment.newInstance(NAV_TYPE_MESSAGE)};

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE};
    private static String[] mTitles = {"主页", "视频", "图片", "家联本", "消息"};

    private static int radioBtnIds[] = {R.id.bottom_home, R.id.bottom_video, R.id.bottom_pic,
            R.id.bottom_jia, R.id.bottom_msg};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        mToolbar.setNavigationIcon(null);
        setTitle(mTitles[NAV_TYPE_MAIN]);
        radioGroup = findViewById(R.id.radioGroup);
        rbHome = findViewById(R.id.bottom_home);
        rbVideo = findViewById(R.id.bottom_video);
        rbPic = findViewById(R.id.bottom_pic);
        rbBook = findViewById(R.id.bottom_jia);
        rbMessage = findViewById(R.id.bottom_msg);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.add(R.id.main_layout, fragmentArray[0]);
        beginTransaction.add(R.id.main_layout, fragmentArray[1]);
        beginTransaction.add(R.id.main_layout, fragmentArray[2]);
        beginTransaction.add(R.id.main_layout, fragmentArray[3]);
        beginTransaction.add(R.id.main_layout, fragmentArray[4]);
        beginTransaction.commit();
        changeFragment(1);
        rbHome.setDrawableSize(100);


    }

    @Override
    protected void initListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.bottom_home:
                    setRight(-1);
                    setTitle(mTitles[NAV_TYPE_MAIN]);
                    changeFragment(1);
                    rbHome.setDrawableSize(100);
                    rbVideo.setDrawableSize(75);
                    rbPic.setDrawableSize(75);
                    rbBook.setDrawableSize(75);
                    rbMessage.setDrawableSize(75);
                    break;
                case R.id.bottom_video:
                    setTitle(mTitles[NAV_TYPE_VIDEO]);
                    setRight(-1);
                    changeFragment(2);
                    rbHome.setDrawableSize(75);
                    rbVideo.setDrawableSize(100);
                    rbPic.setDrawableSize(75);
                    rbBook.setDrawableSize(75);
                    rbMessage.setDrawableSize(75);
                    break;
                case R.id.bottom_pic:
                    setTitle(mTitles[NAV_TYPE_PICTURE]);
                    setRight(-1);
                    changeFragment(3);
                    rbHome.setDrawableSize(75);
                    rbVideo.setDrawableSize(75);
                    rbPic.setDrawableSize(100);
                    rbBook.setDrawableSize(75);
                    rbMessage.setDrawableSize(75);
                    break;
                case R.id.bottom_jia:
                    setTitle(mTitles[NAV_TYPE_PARENT_NOTEBOOK]);
                    setRight(-1);
                    changeFragment(4);
                    rbHome.setDrawableSize(75);
                    rbVideo.setDrawableSize(75);
                    rbPic.setDrawableSize(75);
                    rbBook.setDrawableSize(100);
                    rbMessage.setDrawableSize(75);
                    break;
                case R.id.bottom_msg:
                    setTitle(mTitles[NAV_TYPE_MESSAGE]);
                    setRight(R.menu.item_message_edit);
                    changeFragment(5);
                    rbHome.setDrawableSize(75);
                    rbVideo.setDrawableSize(75);
                    rbPic.setDrawableSize(75);
                    rbBook.setDrawableSize(75);
                    rbMessage.setDrawableSize(100);
                    break;

            }
        });
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

    public void changeFragment(int index) {
        for (int i = 0; i < fragmentArray.length; i++) {
            Fragment fragment = fragmentArray[i];
            if (i + 1 != index) {
                // 隐藏选项卡
                FragmentUtils.hide(fragment);
            } else {
                // 当前选项卡
                currentFragment = fragment;
                FragmentUtils.show(fragment);
            }
        }
    }

    @Override
    public void onRightText(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.write_btn) {
            startActivity(new Intent(mContext, WriteMsgActivity.class));
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment == currentFragment) {
            FragmentUtils.show(fragment);
        } else {
            FragmentUtils.hide(fragment);
        }
    }

    public boolean isUnReadState = false;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handlerPushMessage(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handlerPushMessage(getIntent());
    }

    private void handlerPushMessage(Intent intent) {
        int pushType = intent.getIntExtra(FLAG_PUSH_KEY, 0);

        if (pushType != 0) {
            int index = pushType + 1;

            Fragment fragment = fragmentArray[pushType];
            if (fragment instanceof MsgFragment) {
                isUnReadState = true;
            }
            setTitle(mTitles[pushType]);

//        radioGroup.check(index);
            changeFragment(index);
            //要选中当前的按钮
            radioGroup.check(radioBtnIds[pushType]);
        }
    }
}
