package com.exchange.water.application.start.guide.frame.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.exchange.water.application.R;
import com.exchange.water.application.start.guide.details.GuideDetailsFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lion on 2017/6/3.
 */

public class GuideDetailsPagerAdapter extends FragmentPagerAdapter {

    private int[] mImgResIds = {
            R.mipmap.img_guide_1,
            R.mipmap.img_guide_2,
            R.mipmap.img_guide_3
    };

    private List<GuideDetailsFragment> mFragments = new ArrayList<>();

    public GuideDetailsPagerAdapter(FragmentManager fm) {
        super(fm);
        createFragment();
    }

    private void createFragment() {
        for (Integer resId : mImgResIds) {
            GuideDetailsFragment fragment = new GuideDetailsFragment();
            Bundle data = new Bundle();
            data.putInt("imgResId", resId);
            fragment.setArguments(data);
            mFragments.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mImgResIds.length;
    }
}
