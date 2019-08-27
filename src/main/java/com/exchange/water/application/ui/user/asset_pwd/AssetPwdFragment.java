package com.exchange.water.application.ui.user.asset_pwd;


import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentAssetPwdBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.modify_login_pwd.ModifyLoginPwdPresenter;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class AssetPwdFragment extends BaseTitleFragment<FragmentAssetPwdBinding> implements AssetPwdContract.View ,View.OnClickListener,YunpianCaptchaUtils.OnCaptchaWindowListener{

    private AssetPwdContract.Presenter presenter;



    public static AssetPwdFragment newInstance() {
        Bundle args = new Bundle();
        AssetPwdFragment fragment = new AssetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        initTitle(R.string.set_asset_password);
        YunpianCaptchaUtils.getInstance().setCaptchaWindowListener(this);
        new AssetPwdPresenter(Injection.provideTasksRepository(getContext()), this);

        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);
        mDataBinding.imgIsInvisible2.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_asset_pwd;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.img_is_invisible:

                break;
            case R.id.img_is_invisible2:

                break;
            case R.id.tv_yzm:

                break;
            case R.id.btn_confirm:

                YunpianCaptchaUtils.getInstance().start(getActivity());

                break;

        }
    }

    @Override
    public void onCaptchaSuccess(String data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }

    @Override
    public void setPresenter(AssetPwdContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void accountPwdSuccess(String obj) {

    }

    @Override
    public void accountPwdFail(Integer code, String toastMessage) {

    }


}
