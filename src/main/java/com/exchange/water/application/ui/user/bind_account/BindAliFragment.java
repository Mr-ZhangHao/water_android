package com.exchange.water.application.ui.user.bind_account;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentAliAccountBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindAliFragment extends BaseTitleFragment<FragmentAliAccountBinding> implements BindAccountContact.AliView,YunpianCaptchaUtils.OnCaptchaWindowListener{
    private BindAccountContact.AliPresenter presenter;

    public static BindAliFragment newInstance() {
        Bundle args = new Bundle();
        BindAliFragment fragment = new BindAliFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
            initTitle(R.string.bind_ali_account);
        new BindAliPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.upload.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        YunpianCaptchaUtils.getInstance().setCaptchaWindowListener(this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_ali_account;
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
            case R.id.upload:

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
    public void setPresenter(BindAccountContact.AliPresenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void getBindAliSuccess(String obj) {

    }

    @Override
    public void getBindAliFail(Integer code, String toastMessage) {

    }

    @Override
    public void getUpdateAliSuccess(String obj) {

    }

    @Override
    public void getUpdateAliFail(Integer code, String toastMessage) {

    }

    @Override
    public void uploadBase64PicFail(Integer code, String toastMessage) {

    }

    @Override
    public void uploadBase64PicSuccess(String obj) {

    }
}
