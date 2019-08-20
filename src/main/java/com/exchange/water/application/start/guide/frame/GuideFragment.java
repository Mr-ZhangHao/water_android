package com.exchange.water.application.start.guide.frame;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentGuideBinding;
import com.exchange.water.application.start.guide.frame.adapter.GuideDetailsPagerAdapter;


/**
 * Created by lion on 2017/6/3.
 */

public class GuideFragment extends BaseVDBFragment<FragmentGuideBinding> {

    private GuideDetailsPagerAdapter mAdapter;

    public static GuideFragment newInstance() {
        Bundle args = new Bundle();
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        mAdapter = new GuideDetailsPagerAdapter(getChildFragmentManager());
        mDataBinding.viewPager.setAdapter(mAdapter);

        selectIndicator(0);
        mDataBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void selectIndicator(int position) {
        switch (position) {
            case 0:
                mDataBinding.indicator1.setSelected(true);
                mDataBinding.indicator2.setSelected(false);
                mDataBinding.indicator3.setSelected(false);
                mDataBinding.indicator4.setSelected(false);
                mDataBinding.indicator5.setSelected(false);
                mDataBinding.indicator6.setSelected(false);
                break;
            case 1:
                mDataBinding.indicator1.setSelected(false);
                mDataBinding.indicator2.setSelected(true);
                mDataBinding.indicator3.setSelected(false);
                mDataBinding.indicator4.setSelected(false);
                mDataBinding.indicator5.setSelected(false);
                mDataBinding.indicator6.setSelected(false);
                break;
            case 2:
                mDataBinding.indicator1.setSelected(false);
                mDataBinding.indicator2.setSelected(false);
                mDataBinding.indicator3.setSelected(true);
                mDataBinding.indicator4.setSelected(false);
                mDataBinding.indicator5.setSelected(false);
                mDataBinding.indicator6.setSelected(false);
                break;
            case 3:
                mDataBinding.indicator1.setSelected(false);
                mDataBinding.indicator2.setSelected(false);
                mDataBinding.indicator3.setSelected(false);
                mDataBinding.indicator4.setSelected(true);
                mDataBinding.indicator5.setSelected(false);
                mDataBinding.indicator6.setSelected(false);
                break;
            case 4:
                mDataBinding.indicator1.setSelected(false);
                mDataBinding.indicator2.setSelected(false);
                mDataBinding.indicator3.setSelected(false);
                mDataBinding.indicator4.setSelected(false);
                mDataBinding.indicator5.setSelected(true);
                mDataBinding.indicator6.setSelected(false);
                break;
            case 5:
                mDataBinding.indicator1.setSelected(false);
                mDataBinding.indicator2.setSelected(false);
                mDataBinding.indicator3.setSelected(false);
                mDataBinding.indicator4.setSelected(false);
                mDataBinding.indicator5.setSelected(false);
                mDataBinding.indicator6.setSelected(true);
                break;
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_guide;
    }
}
