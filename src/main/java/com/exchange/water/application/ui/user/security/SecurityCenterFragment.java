package com.exchange.water.application.ui.user.security;

import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentSecurityCenterBinding;
import com.exchange.water.application.ui.user.asset_pwd.AssetPwdFragment;
import com.exchange.water.application.ui.user.bind_email.BindEmailFragment;
import com.exchange.water.application.ui.user.modify_login_pwd.ModifyLoginPwdFragment;
import com.exchange.water.application.utils.ExEventBus;


/**
 * Created by Administrator on 2019/8/23.
 */

public class SecurityCenterFragment extends BaseTitleFragment<FragmentSecurityCenterBinding> implements View.OnClickListener {


    public static SecurityCenterFragment newInstance() {
        Bundle args = new Bundle();
        SecurityCenterFragment fragment = new SecurityCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {
        initTitle(R.string.mine_Security_center);

        mDataBinding.tvBindEmail.setOnClickListener(this);
        mDataBinding.tvModifyLoginPwd.setOnClickListener(this);
        mDataBinding.tvAssetPwd.setOnClickListener(this);
        mDataBinding.checkboxSwitch.setOnClickListener(this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_security_center;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_modify_login_pwd:
                ExEventBus.getDefault().startFragment(ModifyLoginPwdFragment.newInstance());

                break;
            case R.id.tv_bind_email:
                ExEventBus.getDefault().startFragment(BindEmailFragment.newInstance());

                break;
            case R.id.tv_asset_pwd:
                ExEventBus.getDefault().startFragment(AssetPwdFragment.newInstance());

                break;
            case R.id.checkbox_switch:

                break;
        }
    }


}
