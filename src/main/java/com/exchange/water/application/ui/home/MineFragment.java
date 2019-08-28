package com.exchange.water.application.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentMineBinding;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.ui.home.contact_us.ContactUsFragment;
import com.exchange.water.application.ui.home.help.HelpFragment;
import com.exchange.water.application.ui.user.bind_account.BindAccountFragment;
import com.exchange.water.application.ui.user.credit.CreditFragment;
import com.exchange.water.application.ui.user.login.LoginFragment;
import com.exchange.water.application.ui.user.security.SecurityCenterFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.kongzue.dialog.v2.SelectDialog;

/**
 * Created by Administrator on 2019/8/16.
 * 我的页面
 */

public class MineFragment extends BaseVDBFragment<FragmentMineBinding> implements View.OnClickListener {

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
       mDataBinding.tvLoginOut.setOnClickListener(this);

        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mDataBinding.statusBar.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.statusBar.setLayoutParams(layoutParams);
        updateUI(new Runnable() {
            @Override
            public void run() {
                mDataBinding.tvCertified.setVisibility(MyApplication.getApp().isLogin() ? View.VISIBLE : View.GONE);
                mDataBinding.tvLoginOut.setVisibility(MyApplication.getApp().isLogin() ? View.VISIBLE : View.GONE);

                if (MyApplication.getApp().isLogin()) {
                    mDataBinding.tvName.setText(MyApplication.getApp().getCurrentUser().getM_name_hidden());
                  /*  if (!MyApplication.getApp().getCurrentUser().getHead_img().isEmpty()) {
                        Glide.with(getContext()).load(MyApplication.getApp().getCurrentUser().getHead_img()).into(mDataBinding.imgHeadPortrait);
                    }*/
              mDataBinding.tvCertified.setText(MyApplication.getApp().getCurrentUser().getAuth_grade() == 0 ? getContext().getResources().getText(R.string.mine_certified) : getContext().getResources().getText(R.string.mine_verified));

                } else {
                    mDataBinding.tvName.setText(getContext().getResources().getText(R.string.mine_login_or_registered));
                 //   Glide.with(getContext()).load(getContext().getResources().getDrawable(R.mipmap.icon_tx)).into(mDataBinding.imgHeadPortrait);

                }
            }
        });

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

                if (!MyApplication.getApp().isLogin()) {
                    WonderfulToastUtils.showToast(_mActivity.getResources().getString(R.string.please_login));
                    ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
                    return;
                }
                ExEventBus.getDefault().startFragment(SecurityCenterFragment.newInstance());

                break;
            case R.id.ll_Verified:
                if (!MyApplication.getApp().isLogin()) {
                    WonderfulToastUtils.showToast(_mActivity.getResources().getString(R.string.please_login));
                    ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
                    return;
                }
                ExEventBus.getDefault().startFragment(CreditFragment.newInstance());

                break;
            case R.id.ll_Payment_binding:

                if (!MyApplication.getApp().isLogin()) {
                    WonderfulToastUtils.showToast(_mActivity.getResources().getString(R.string.please_login));
                    ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
                    return;
                }

                ExEventBus.getDefault().startFragment(BindAccountFragment.newInstance());


                break;
            case R.id.ll_invite_friends:

                break;
            case R.id.ll_Platform_announcement:

                break;
            case R.id.ll_Help_center:
                ExEventBus.getDefault().startFragment(HelpFragment.newInstance());

                break;
            case R.id.ll_contact_us:
                ExEventBus.getDefault().startFragment(ContactUsFragment.newInstance());
                break;
            case R.id.ll_WATER:

                break;
            case R.id.tv_login_out:
                SelectDialog.show(getActivity(), getResources().getString(R.string.me_signOut),  getResources().getString(R.string.me_signOut_sure),  getResources().getString(R.string.me_signOut_dropout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setFragmentResult(RESULT_OK, null);
                        MyApplication.getApp().loginOut();

                    }
                }, getResources().getString(R.string.signUp_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                break;
        }
    }
}
