package com.exchange.water.application.ui.user.bind_account;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentBankAccountBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindBankFragment extends BaseTitleFragment<FragmentBankAccountBinding> implements BindAccountContact.BankView ,YunpianCaptchaUtils.OnCaptchaWindowListener{


    private BindAccountContact.BankPresenter presenter;

    public static BindBankFragment newInstance() {
        Bundle args = new Bundle();
        BindBankFragment fragment = new BindBankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(BindAccountContact.BankPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onBind() {

        initTitle(R.string.bind_bank_account);
        new BindBankPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);

    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.img_is_invisible:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edAssetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edAssetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;


            case R.id.btn_confirm:

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;

        }
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bank_account;
    }

    @Override
    public void getBindBankSuccess(String obj) {

    }

    @Override
    public void getBindBankFail(Integer code, String toastMessage) {

    }

    @Override
    public void getUpdateBankSuccess(String obj) {

    }

    @Override
    public void getUpdateBankFail(Integer code, String toastMessage) {

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }
}
