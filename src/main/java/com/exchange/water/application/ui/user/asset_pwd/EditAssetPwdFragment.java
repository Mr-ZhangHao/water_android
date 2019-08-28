package com.exchange.water.application.ui.user.asset_pwd;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentModifyAssetPwdBinding;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class EditAssetPwdFragment extends BaseTitleFragment<FragmentModifyAssetPwdBinding> implements AssetPwdContract.EditView ,YunpianCaptchaUtils.OnCaptchaWindowListener{
    private AssetPwdContract.EditPresenter presenter;


    public static EditAssetPwdFragment newInstance() {
        Bundle args = new Bundle();
        EditAssetPwdFragment fragment = new EditAssetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setPresenter(AssetPwdContract.EditPresenter presenter) {
    this.presenter = presenter;
    }

    @Override
    public void sendSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    @Override
    public void sendCodeFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);

    }

    @Override
    public void editAccountPedSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);

        updateUI(new Runnable() {
            @Override
            public void run() {
                pop();
            }
        },1500);
    }

    @Override
    public void editAccountPedFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);

    }

    @Override
    protected void onBind() {
        initTitle(R.string.Modify_asset_password);
        YunpianCaptchaUtils.getInstance().setCaptchaWindowListener(this);
        new EditPwdPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);
        mDataBinding.imgIsInvisible2.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        mDataBinding.imgOldInvisible.setOnClickListener(this);


        mDataBinding.edCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  edAssetPwd =mDataBinding.edModifyPwd.getText().toString().trim();
                final String  edAssetConfirmPwd =mDataBinding.edModifyConfirmPwd.getText().toString().trim();
                final String  edModifyOldPwd =mDataBinding.edModifyOldPwd.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnConfirm.setEnabled(!TextUtils.isEmpty(edAssetPwd)&&!TextUtils.isEmpty(edAssetConfirmPwd)&&!TextUtils.isEmpty(edCode)&&!TextUtils.isEmpty(edModifyOldPwd));
                    }
                });
            }
        });
        mDataBinding.edModifyConfirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  edAssetPwd =mDataBinding.edModifyPwd.getText().toString().trim();
                final String  edAssetConfirmPwd =mDataBinding.edModifyConfirmPwd.getText().toString().trim();
                final String  edModifyOldPwd =mDataBinding.edModifyOldPwd.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnConfirm.setEnabled(!TextUtils.isEmpty(edAssetPwd)&&!TextUtils.isEmpty(edAssetConfirmPwd)&&!TextUtils.isEmpty(edCode)&&!TextUtils.isEmpty(edModifyOldPwd));
                    }
                });
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_modify_asset_pwd;
    }

    @Override
    public void onCaptchaSuccess(String msg) {
        if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
            presenter.sendCode(MyApplication.getApp().getCurrentUser().getM_name(),msg);
        }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
            presenter.sendCode(MyApplication.getApp().getCurrentUser().getM_email(),msg);
        }
        mDataBinding.tvYzm.setEnabled(false);
    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, msg);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.img_old_invisible:
                if (mDataBinding.imgOldInvisible.isChecked()){
                    mDataBinding.edModifyOldPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edModifyOldPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_is_invisible:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edModifyPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edModifyPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_is_invisible2:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edModifyConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edModifyConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_yzm:
                YunpianCaptchaUtils.getInstance().start(getActivity());

                break;
            case R.id.btn_confirm:
                final String  edAssetPwd =mDataBinding.edModifyPwd.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                final String  edAssetConfirmPwd =mDataBinding.edModifyConfirmPwd.getText().toString().trim();
                final String  edModifyOldPwd =mDataBinding.edModifyOldPwd.getText().toString().trim();

                if (TextUtils.isEmpty(edModifyOldPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_old_pwd_empty_hint));
                    mDataBinding.edModifyOldPwd.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(edAssetPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_pwd_empty_hint));
                    mDataBinding.edModifyPwd.requestFocus();
                    return;
                }
                if (!StrUtil.check(edAssetPwd, StrUtil.wallet_pwdCheck)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_check_pwd));
                    mDataBinding.edModifyPwd.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(edAssetConfirmPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_Confirm_pwd));
                    mDataBinding.edModifyConfirmPwd.requestFocus();
                    return;
                }
                if (!edAssetPwd.equals(edAssetConfirmPwd)) {
                    WonderfulToastUtils.showToast(R.string.signUp_hint_pwd_contrast);
                    return;
                }

             presenter.editAccountPed(MyApplication.getApp().getCurrentUser().getToken(),edAssetConfirmPwd,edModifyOldPwd,edCode);
                break;

        }
    }

    private CountDownTimer timer;

    private void fillCodeView(long time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mDataBinding.tvYzm.setText(getContext().getResources().getString(R.string.re_send) + "（" + millisUntilFinished / 1000 + "）");
            }

            @Override
            public void onFinish() {
                mDataBinding.tvYzm.setText(R.string.send_code);
                mDataBinding.tvYzm.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        YunpianCaptchaUtils.getInstance().onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
