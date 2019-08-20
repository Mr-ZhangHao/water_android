package com.exchange.water.application.ui.home;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentAssetBinding;

/**
 * Created by Administrator on 2019/8/16.
 * 资产页面
 */

public class AssetFragment extends BaseVDBFragment <FragmentAssetBinding>{

    public static AssetFragment newInstance() {
        Bundle args = new Bundle();
        AssetFragment fragment = new AssetFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_asset;
    }
}
