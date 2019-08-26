package com.exchange.water.application.ui.user.modify_login_pwd;


import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentModifyPwdBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.bind_email.BindEmailContract;
import com.exchange.water.application.ui.user.bind_email.BindEmailFragment;
import com.exchange.water.application.ui.user.bind_email.BindEmailPresenter;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class ModifyLoginPwdFragment extends BaseTitleFragment<FragmentModifyPwdBinding> implements ModifyLoginPwdContract.View ,View.OnClickListener,YunpianCaptchaUtils.OnCaptchaWindowListener{


    private ModifyLoginPwdContract.Presenter presenter;



    public static ModifyLoginPwdFragment newInstance() {
        Bundle args = new Bundle();
        ModifyLoginPwdFragment fragment = new ModifyLoginPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.Modify_Login_password);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);
        new ModifyLoginPwdPresenter(Injection.provideTasksRepository(getContext()), this);

          mDataBinding.btnConfirm.setOnClickListener(this);
          mDataBinding.tvYzm.setOnClickListener(this);
          mDataBinding.imgOldInvisible.setOnClickListener(this);
          mDataBinding.imgIsInvisible2.setOnClickListener(this);
          mDataBinding.imgIsInvisible.setOnClickListener(this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_modify_pwd;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.img_old_invisible:

                break;
            case R.id.img_is_invisible:

                break;
            case R.id.img_is_invisible2:

                break;
            case R.id.tv_yzm:

                break;
            case R.id.btn_confirm:

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;

        }
    }
    @Override
    public void setPresenter(ModifyLoginPwdContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void sendEditLoginPwdCodeSuccess(String obj) {

    }

    @Override
    public void sendEditLoginPwdCodeFail(Integer code, String toastMessage) {

    }

    @Override
    public void editPwdSuccess(String obj) {

    }

    @Override
    public void editPwdFail(Integer code, String toastMessage) {

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }
}
