package com.exchange.water.application.ui.user.forgot_pwd;

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
import com.exchange.water.application.databinding.FragmentForgotPwd2Binding;
import com.exchange.water.application.ui.user.signup.SignUpFragment2;
import com.exchange.water.application.ui.user.signup.SignUpPresenter;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;

/**
 * Created by Administrator on 2019/8/22.
 */

public class PhoneForgotFragment2 extends BaseVDBFragment <FragmentForgotPwd2Binding>implements ForgotPwdContract.PhoneView,View.OnClickListener{

    private static final String KEY_ACCOUNT = "key_account";
    private static final String KEY_AREACODE = "key_areacode";
    private static final String KEY_ISPHONE = "key_isPhone";
    private static final String KEY_CODE = "key_edCode";
    private String mCode;
    private String mAccount;
    private String mAreacode;
    private boolean mIsPhone;
    private ForgotPwdContract.PhonePresenter mPresenter;

    public static PhoneForgotFragment2 newInstance(String Account, String Areacode,String edCode, boolean isPhone) {
        Bundle args = new Bundle();
        args.putString(KEY_ACCOUNT, Account);
        args.putString(KEY_AREACODE, Areacode);
        args.putBoolean(KEY_ISPHONE, isPhone);
        args.putString(KEY_CODE, edCode);
        PhoneForgotFragment2 fragment = new PhoneForgotFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        if (getArguments() != null) {
            mAccount = getArguments().getString(KEY_ACCOUNT);
            mAreacode = getArguments().getString(KEY_CODE);
            mIsPhone = getArguments().getBoolean(KEY_ISPHONE);
            mCode = getArguments().getString(KEY_ISPHONE);
        }
        new PhoneForgotPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.cancel.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        mDataBinding.imgIsInvisible2.setOnClickListener(this);
        mDataBinding.btnConfirm.setOnClickListener(this);
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
                final String  edSignUpPwd =mDataBinding.edSignUpPwd.getText().toString().trim();
                final String  edSignUpConfirmPwd =mDataBinding.edSignUpConfirmPwd.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnConfirm.setEnabled(!TextUtils.isEmpty(edSignUpPwd)&&!TextUtils.isEmpty(edSignUpConfirmPwd));
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

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

            case R.id.btn_Confirm:
                final String  edSignUpPwd =mDataBinding.edSignUpPwd.getText().toString().trim();
                final String  edSignUpConfirmPwd =mDataBinding.edSignUpConfirmPwd.getText().toString().trim();

                if (TextUtils.isEmpty(mCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
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
                mPresenter.forgotPwd(mAccount,mCode,mAreacode,edSignUpPwd);

                break;
        }
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_forgot_pwd2;
    }

    @Override
    public void setPresenter(ForgotPwdContract.PhonePresenter presenter) {
        this.mPresenter = presenter;

    }

    @Override
    public void phoneForgotCodeSuccess(String obj) {

    }

    @Override
    public void phoneForgotCodeFail(Integer code, String toastMessage) {

    }

    @Override
    public void forgotPwdSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);

    }

    @Override
    public void forgotPwdFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);

    }
}
