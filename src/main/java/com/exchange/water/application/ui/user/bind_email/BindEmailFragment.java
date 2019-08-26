package com.exchange.water.application.ui.user.bind_email;


import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentBindEmailBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.login.LoginContract;
import com.exchange.water.application.ui.user.login.LoginPresenter;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class BindEmailFragment extends BaseTitleFragment<FragmentBindEmailBinding> implements BindEmailContract.View ,YunpianCaptchaUtils.OnCaptchaWindowListener{


    private BindEmailContract.Presenter presenter;



    public static BindEmailFragment newInstance() {
        Bundle args = new Bundle();
        BindEmailFragment fragment = new BindEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.Security_center_modify_bingd_email);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);
        new BindEmailPresenter(Injection.provideTasksRepository(getContext()), this);

        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_yzm:

                break;
            case R.id.btn_confirm:

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;

        }
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bind_email;
    }

    @Override
    public void setPresenter(BindEmailContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void bindEmailSuccess(String obj) {

    }

    @Override
    public void bindEmailFail(Integer code, String toastMessage) {

    }

    @Override
    public void sendEmailCodeSuccess(String obj) {

    }

    @Override
    public void sendEmailCodeFail(Integer code, String toastMessage) {

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }
}
