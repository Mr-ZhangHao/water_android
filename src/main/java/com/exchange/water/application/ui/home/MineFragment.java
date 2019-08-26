package com.exchange.water.application.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentMainBinding;
import com.exchange.water.application.databinding.FragmentMineBinding;
import com.exchange.water.application.ui.user.login.LoginFragment;
import com.exchange.water.application.ui.user.signup.SignUpFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;

/**
 * Created by Administrator on 2019/8/16.
 * 我的页面
 */

public class MineFragment extends BaseVDBFragment <FragmentMineBinding>implements View.OnClickListener{

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {
        mDataBinding.llGotoLogin.setOnClickListener(this);
        mDataBinding.llContactUs.setOnClickListener(this);
        mDataBinding.llHelpCenter.setOnClickListener(this);
        mDataBinding.llInviteFriends.setOnClickListener(this);
        mDataBinding.llPaymentBinding.setOnClickListener(this);
        mDataBinding.llWATER.setOnClickListener(this);
        mDataBinding.llVerified.setOnClickListener(this);
        mDataBinding.llSecurityCenter.setOnClickListener(this);
        mDataBinding.llPlatformAnnouncement.setOnClickListener(this);

        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mDataBinding.statusBar.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.statusBar.setLayoutParams(layoutParams);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_goto_login:
                 ExEventBus.getDefault().startFragment(LoginFragment.newInstance());

                break;

            case R.id.ll_Security_center:

                break;
            case R.id.ll_Verified:

                break;
            case R.id.ll_Payment_binding:

                break;
            case R.id.ll_invite_friends:

                break;
            case R.id.ll_Platform_announcement:

                break;
            case R.id.ll_Help_center:

                break;
            case R.id.ll_contact_us:

                break;
            case R.id.ll_WATER:

                break;
        }
    }
}
