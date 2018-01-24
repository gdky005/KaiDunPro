package com.kaidun.pro.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.fresco.helper.utils.DensityUtil;
import com.kaidun.pro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.ll_indicate)
    LinearLayout navLay;
    @BindView(R.id.vp_welcome)
    ViewPager viewPager;
    @BindView(R.id.btn_skip)
    Button skipBtn;
    @BindView(R.id.btn_start)
    Button startBtn;
    SimplePagerAdapter pagerAdapter;
    private List<View> views;
    private int curPos;
    private int drawables[] = new int[]{R.drawable.img1_default, R.drawable.img2_default, R.drawable.img3_default};
    private int tips[] = new int[]{R.string.tip_content1, R.string.tip_content2, R.string.tip_content3};
    private int tipTitles[] = new int[]{R.string.tip_title1, R.string.tip_title2, R.string.tip_title3};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome_page;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        views = new ArrayList<>();
        View view;
        for (int i = 0; i < drawables.length; i++) {
            view = LayoutInflater.from(this).inflate(R.layout.item_welcome_page, null);
            ((ImageView) view.findViewById(R.id.iv_welcome)).setImageResource(drawables[i]);
            ((TextView) view.findViewById(R.id.tv_tip)).setText(getString(tips[i]));
            ((TextView) view.findViewById(R.id.tv_tip_title)).setText(getString(tipTitles[i]));
            views.add(view);
        }
        addNavIcon();
        pagerAdapter = new SimplePagerAdapter();
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == views.size() - 1) {
                    skipBtn.setVisibility(View.GONE);
                    navLay.setVisibility(View.GONE);
                    startBtn.setVisibility(View.VISIBLE);
                } else {
                    skipBtn.setVisibility(View.VISIBLE);
                    navLay.setVisibility(View.VISIBLE);
                    startBtn.setVisibility(View.GONE);
                }
                selectNav(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_skip, R.id.btn_start})
    public void onViewClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void selectNav(int position) {
        if (curPos != position) {
            navLay.getChildAt(curPos).setSelected(false);
            navLay.getChildAt(position).setSelected(true);
            curPos = position;
        }

    }

    private void addNavIcon() {
        ImageView icon = null;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dipToPixels(this, 20), ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(DensityUtil.dipToPixels(this, 10), 0, DensityUtil.dipToPixels(this, 10), 0);
        for (int i = 0; i < views.size(); i++) {
            icon = new ImageView(this);
            icon.setScaleType(ImageView.ScaleType.CENTER);
            icon.setImageResource(R.drawable.selector_vp_indicator_dot);
            if (i == 0) {
                icon.setSelected(true);
            } else {
                icon.setSelected(false);
            }
            icon.setLayoutParams(params);
            navLay.addView(icon);
        }

    }

    class SimplePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {

            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
