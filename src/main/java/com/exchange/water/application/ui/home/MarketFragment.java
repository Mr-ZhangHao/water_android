package com.exchange.water.application.ui.home;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentMarketBinding;

/**
 * Created by Administrator on 2019/8/16.
 *
 * 行情页面
 */

public class MarketFragment extends BaseVDBFragment <FragmentMarketBinding>{

    public static MarketFragment newInstance() {
        Bundle args = new Bundle();
        MarketFragment fragment = new MarketFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_market;
    }
}
