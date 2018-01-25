package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lmj on 2018/1/23.
 */

public class VideoFragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> data;

    private String[] titles;

    public VideoFragmentAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null ? titles[position] : "";
    }
}
