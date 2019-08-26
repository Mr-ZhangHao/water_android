package com.exchange.water.application.ui.user.signup;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.LinearLayout;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentSignup2Binding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.login.LoginPresenter;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulStringUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

import org.json.JSONObject;

/**
 * Created by Administrator on 2019/8/21.
 */

public class SignUpFragment2 extends BaseVDBFragment <FragmentSignup2Binding> implements View.OnClickListener,SignUpContract.PhoneView,YunpianCaptchaUtils.OnCaptchaWindowListener{

    private static final String KEY_ACCOUNT = "key_account";
    private static final String KEY_CODE = "key_areacode";
    private static final String KEY_ISPHONE = "key_isPhone";
    private String mAccount;
    private String mAreacode;
    private boolean mIsPhone;

    private SignUpContract.PhonePresenter presenter;

    public static SignUpFragment2 newInstance(String Account,String Areacode,boolean isPhone) {
        Bundle args = new Bundle();
        args.putString(KEY_ACCOUNT, Account);
        args.putString(KEY_CODE, Areacode);
        args.putBoolean(KEY_ISPHONE, isPhone);
        SignUpFragment2 fragment = new SignUpFragment2();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {
        if (getArguments() != null) {
            mAccount = getArguments().getString(KEY_ACCOUNT);
            mAreacode = getArguments().getString(KEY_CODE);
            mIsPhone = getArguments().getBoolean(KEY_ISPHONE);
        }
        new SignUpPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.cancel.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        mDataBinding.imgIsInvisible2.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);
        mDataBinding.btnSignUpConfirm.setOnClickListener(this);
        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mDataBinding.cancel.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.cancel.setLayoutParams(layoutParams);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mDataBinding.edSignUpConfirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                final String  edSignUpPwd =mDataBinding.edSignUpPwd.getText().toString().trim();
                final String  edSignUpConfirmPwd =mDataBinding.edSignUpConfirmPwd.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnSignUpConfirm.setEnabled(!TextUtils.isEmpty(edCode)&&!TextUtils.isEmpty(edSignUpPwd)&&!TextUtils.isEmpty(edSignUpConfirmPwd));
                    }
                });
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_signup2;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.tv_yzm:
                sendCode();
                break;
            case R.id.cancel:
                pop();

                break;
            case R.id.img_is_invisible:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edSignUpPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edSignUpPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;

            case R.id.img_is_invisible2:
                if (mDataBinding.imgIsInvisible2.isChecked()){
                    mDataBinding.edSignUpConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edSignUpConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                break;

            case R.id.btn_signUp_Confirm:
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                final String  edSignUpPwd =mDataBinding.edSignUpPwd.getText().toString().trim();
                final String  edSignUpConfirmPwd =mDataBinding.edSignUpConfirmPwd.getText().toString().trim();
                final String  edSignUpInvitationCode =mDataBinding.edSignUpInvitationCode.getText().toString().trim();

                if (TextUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(edSignUpPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_pwd_empty_hint));
                    mDataBinding.edSignUpPwd.requestFocus();
                    return;
                }

                if (!StrUtil.check(edSignUpPwd, StrUtil.pwdCheck)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_check_pwd));
                    mDataBinding.edSignUpConfirmPwd.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(edSignUpConfirmPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_hint_Confirm_pwd));
                    mDataBinding.edSignUpConfirmPwd.requestFocus();
                    return;
                }

                if (!edSignUpPwd.equals(edSignUpConfirmPwd)) {
                    WonderfulToastUtils.showToast(R.string.signUp_hint_pwd_contrast);
                    return;
                }

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;
        }
    }

    private CountDownTimer timer;
    private void sendCode() {
         presenter.phoneCode(mAccount,mAreacode);
         mDataBinding.tvYzm.setEnabled(false);
    }
    private void fillCodeView(long time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mDataBinding.tvYzm.setText(WonderfulToastUtils.getString(R.string.re_send)+"(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
                mDataBinding.tvYzm.setText(WonderfulToastUtils.getString(R.string.send_code));
                mDataBinding.tvYzm.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void setPresenter(SignUpContract.PhonePresenter presenter) {
        this.presenter =presenter;

    }

    @Override
    public void phoneCodeSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    @Override
    public void phoneCodeFail(Integer code, String toastMessage) {
        mDataBinding.tvYzm.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void signUpByPhoneSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);

    }

    @Override
    public void signUpByPhoneFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);

    }

    @Override
    public void signUpByEmailSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);

    }

    @Override
    public void signUpByEmailFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {
        final String  edCode =mDataBinding.edCode.getText().toString().trim();
        final String  edSignUpPwd =mDataBinding.edSignUpPwd.getText().toString().trim();
        final String  edSignUpInvitationCode =mDataBinding.edSignUpInvitationCode.getText().toString().trim();

        if (mIsPhone){
            presenter.signUpByPhone(mAreacode,mAccount,edSignUpPwd,edCode,edSignUpInvitationCode);
        }else {
            presenter.signUpByEmail(mAccount,edSignUpPwd,edCode,edSignUpInvitationCode);
        }
    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulToastUtils.showToast(msg);

    }
}
