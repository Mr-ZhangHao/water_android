package com.exchange.water.application.ui.home;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;

/**
 * Created by Administrator on 2019/8/16.
 * 币币交易页面
 */

public class TradingFragment  extends BaseVDBFragment{


    public static TradingFragment newInstance() {
        Bundle args = new Bundle();
        TradingFragment fragment = new TradingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_trading;
    }
}
