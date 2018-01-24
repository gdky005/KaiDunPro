package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lmj on 2018/1/23.
 */

public class MsgFragmentPagerAdapter extends FragmentPagerAdapter {


    private Fragment[] data;

    private String[] titles;

    public MsgFragmentPagerAdapter(FragmentManager fm, Fragment[] data) {
        super(fm);
        this.data= data;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return data[position];
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null ? titles[position] : "";
    }
}
