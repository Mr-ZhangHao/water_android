package com.exchange.water.application.ui.user.security;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentSecurityCenterBinding;
import com.exchange.water.application.ui.user.country.CountryFragment;

/**
 * Created by Administrator on 2019/8/23.
 */

public class SecurityCenterFragment extends BaseTitleFragment<FragmentSecurityCenterBinding> {


    public static SecurityCenterFragment newInstance() {
        Bundle args = new Bundle();
        SecurityCenterFragment fragment = new SecurityCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_security_center;
    }
}
