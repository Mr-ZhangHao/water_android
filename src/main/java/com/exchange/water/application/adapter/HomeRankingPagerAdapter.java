package com.exchange.water.application.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.exchange.water.application.R;
import com.exchange.water.application.ui.home.ranking.RankingPagerFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2019/8/19.
 */

public class HomeRankingPagerAdapter  extends BaseFragmentAdapter{

    public HomeRankingPagerAdapter(Activity context, FragmentManager fm) {
        super(context, fm);
        mTitles = new String[]{
                context.getResources().getString(R.string.home_list_text_Rising),
                context.getResources().getString(R.string.home_list_text_Trading),
                context.getResources().getString(R.string.home_list_text_New_currency)

        };
        mFragments = new SupportFragment[]{
                RankingPagerFragment.newInstance("Rising"),
                RankingPagerFragment.newInstance("Trading"),
                RankingPagerFragment.newInstance("Currency"),

        };
    }
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        for (SupportFragment fragment : mFragments) {
            fragment.onFragmentResult(requestCode, resultCode, data);
        }
    }
}
