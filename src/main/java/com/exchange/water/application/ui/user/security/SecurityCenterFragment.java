package com.exchange.water.application.ui.user.security;

import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentSecurityCenterBinding;
import com.exchange.water.application.ui.user.asset_pwd.AssetPwdFragment;
import com.exchange.water.application.ui.user.asset_pwd.EditAssetPwdFragment;
import com.exchange.water.application.ui.user.bind_email.BindEmailFragment;
import com.exchange.water.application.ui.user.modify_login_pwd.ModifyLoginPwdFragment;
import com.exchange.water.application.utils.ExEventBus;


/**
 * Created by Administrator on 2019/8/23.
 */

public class SecurityCenterFragment extends BaseTitleFragment<FragmentSecurityCenterBinding> implements View.OnClickListener {

    private boolean isSetAssetPwd ;
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

        updateUI(new Runnable() {
            @Override
            public void run() {

                if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()&&MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
                    mDataBinding.llBindEmail.setVisibility(View.GONE);
                    mDataBinding.vBing.setVisibility(View.GONE);

                } else if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
                    mDataBinding.tvBind.setText(getContext().getResources().getText(R.string.Security_center_modify_bingd_email));
                }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
                    mDataBinding.tvBind.setText(getContext().getResources().getText(R.string.Security_center_modify_bingd_phone));
                }

                if (MyApplication.getApp().getCurrentUser().getM_security_pwd().equals("1")){
                    mDataBinding.tvAssetPwd.setText(getContext().getResources().getText(R.string.Security_center_modify));
                    isSetAssetPwd =true;
                }else {
                    mDataBinding.tvAssetPwd.setText(getContext().getResources().getText(R.string.Security_center_Setting));
                    isSetAssetPwd =false;

                }


            }
        });


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
                if (isSetAssetPwd){
                    ExEventBus.getDefault().startFragment(EditAssetPwdFragment.newInstance());

                }else {
                    ExEventBus.getDefault().startFragment(AssetPwdFragment.newInstance());

                }
                break;
            case R.id.checkbox_switch:

                break;
        }
    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
         updateUI(new Runnable() {
             @Override
             public void run() {
                 if (MyApplication.getApp().getCurrentUser().getM_security_pwd().equals("1")){
                     mDataBinding.tvAssetPwd.setText(getContext().getResources().getText(R.string.Security_center_modify));

                 }else {
                     mDataBinding.tvAssetPwd.setText(getContext().getResources().getText(R.string.Security_center_Setting));

                 }
                 if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()&&MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
                     mDataBinding.llBindEmail.setVisibility(View.GONE);
                     mDataBinding.vBing.setVisibility(View.GONE);
                 }
             }
         });
        }
    }

}
