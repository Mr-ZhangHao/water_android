package com.exchange.water.application.ui.home;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentMainBinding;

/**
 * Created by Administrator on 2019/8/16.
 * 我的页面
 */

public class MineFragment extends BaseVDBFragment <FragmentMainBinding>{

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }
}
