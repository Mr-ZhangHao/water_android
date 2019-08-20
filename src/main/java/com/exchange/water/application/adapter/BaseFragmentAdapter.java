package com.exchange.water.application.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flyco.tablayout.listener.CustomTabEntity;


import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    protected Activity mContext;
    protected SupportFragment[] mFragments = {};

    protected String[] mTitles;

    public BaseFragmentAdapter(Activity context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public SupportFragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public ArrayList<CustomTabEntity> getTitles() {
        ArrayList<CustomTabEntity> data = new ArrayList<>();
        for (String title : mTitles) {
            data.add(new CommonTabData(title));
        }
        return data;
    }
}
