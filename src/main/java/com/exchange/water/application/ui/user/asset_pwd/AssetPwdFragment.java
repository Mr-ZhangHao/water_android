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
import com.exchange.water.application.app.UrlFactory;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentAssetPwdBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.modify_login_pwd.ModifyLoginPwdPresenter;
import com.exchange.water.application.utils.AccountSettings;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;
import com.exchange.water.application.utils.okhttp.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;

import static com.exchange.water.application.app.GlobalConstant.JSON_ERROR;
import static com.exchange.water.application.app.GlobalConstant.OKHTTP_ERROR;
import static com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils.get;

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

        mDataBinding.edCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  edAssetPwd =mDataBinding.edAssetPwd.getText().toString().trim();
                final String  edAssetConfirmPwd =mDataBinding.edAssetConfirmPwd.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnConfirm.setEnabled(!TextUtils.isEmpty(edAssetPwd)&&!TextUtils.isEmpty(edAssetConfirmPwd)&&!TextUtils.isEmpty(edCode));
                    }
                });
            }
        });
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
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edAssetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edAssetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_is_invisible2:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edAssetConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edAssetConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_yzm:
                YunpianCaptchaUtils.getInstance().start(getActivity());

                break;
            case R.id.btn_confirm:
                final String  edAssetPwd =mDataBinding.edAssetPwd.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                final String  edAssetConfirmPwd =mDataBinding.edAssetConfirmPwd.getText().toString().trim();


                if (TextUtils.isEmpty(edAssetPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_pwd_empty_hint));
                    mDataBinding.edAssetPwd.requestFocus();
                    return;
                }
                if (!StrUtil.check(edAssetPwd, StrUtil.wallet_pwdCheck)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_check_pwd));
                    mDataBinding.edAssetPwd.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(edAssetConfirmPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_Confirm_pwd));
                    mDataBinding.edAssetConfirmPwd.requestFocus();
                    return;
                }
                if (!edAssetPwd.equals(edAssetConfirmPwd)) {
                    WonderfulToastUtils.showToast(R.string.signUp_hint_pwd_contrast);
                    return;
                }
                if (TextUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }

                presenter.accountPwd(MyApplication.getApp().getCurrentUser().getToken(),edAssetConfirmPwd,edCode);
                break;

        }
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
    @Override
    public void onCaptchaSuccess(String data) {
        if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
            presenter.sendCode(MyApplication.getApp().getCurrentUser().getM_name(),data);
        }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
            presenter.sendCode(MyApplication.getApp().getCurrentUser().getM_email(),data);
        }
        mDataBinding.tvYzm.setEnabled(false);

    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, msg);
    }

    @Override
    public void setPresenter(AssetPwdContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void sendSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    @Override
    public void sendCodeFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);
        mDataBinding.tvYzm.setEnabled(true);

    }

    @Override
    public void accountPwdSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);

        updateUI(new Runnable() {
            @Override
            public void run() {
                MyApplication.getApp().getCurrentUser().setM_security_pwd("1");
                setFragmentResult(RESULT_OK, null);
                pop();
            }
        },1500);
    }



    @Override
    public void accountPwdFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -code, toastMessage);

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
}
