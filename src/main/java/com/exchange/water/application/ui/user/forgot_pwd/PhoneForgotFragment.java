package com.exchange.water.application.ui.user.forgot_pwd;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentForgotPwdBinding;

import com.exchange.water.application.ui.user.country.CountryFragment;
import com.exchange.water.application.ui.user.signup.SignUpFragment2;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulStringUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

import org.json.JSONObject;



public class PhoneForgotFragment extends BaseVDBFragment<FragmentForgotPwdBinding> implements ForgotPwdContract.PhoneView,YunpianCaptchaUtils.OnCaptchaWindowListener {


    private ForgotPwdContract.PhonePresenter mPresenter;
    private String mEditAccounts;
    private String mArea;

    public static PhoneForgotFragment newInstance() {
        Bundle args = new Bundle();
        PhoneForgotFragment fragment = new PhoneForgotFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        mDataBinding.cancel.setOnClickListener(this);
        mDataBinding.selectCountry.setOnClickListener(this);
        mDataBinding.tvType.setOnClickListener(this);
        mDataBinding.getYzm.setOnClickListener(this);
        mDataBinding.btnNext.setOnClickListener(this);
        new PhoneForgotPresenter(Injection.provideTasksRepository(getContext()), this);
        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mDataBinding.cancel.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.cancel.setLayoutParams(layoutParams);
        YunpianCaptchaUtils.getInstance().setCaptchaWindowListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mDataBinding.edCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                final String  editAccount =mDataBinding.editAccount.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnNext.setEnabled(!TextUtils.isEmpty(edCode)&&!TextUtils.isEmpty(editAccount));
                    }
                });
            }
        });

    }

    @Override
    public void setPresenter(ForgotPwdContract.PhonePresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void phoneForgotCodeSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    @Override
    public void phoneForgotCodeFail(Integer code, String toastMessage) {
        mDataBinding.getYzm.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void forgotPwdSuccess(String obj) {

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
    public void forgotPwdFail(Integer code, String toastMessage) {

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
                mDataBinding.getYzm.setText(getContext().getResources().getString(R.string.re_send) + "（" + millisUntilFinished / 1000 + "）");
            }

            @Override
            public void onFinish() {
                mDataBinding.getYzm.setText(R.string.send_code);
                mDataBinding.getYzm.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_forgot_pwd;
    }
    private boolean type = true;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.cancel:
                pop();
                break;
            case R.id.tv_type:
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        if (type){
                            //邮箱找回
                            type=false;
                            mDataBinding.tvType.setText(R.string.forget_phone_Mobile);
                            mDataBinding.tvTitle.setText(R.string.forget_email_Mobile);
                            mDataBinding.editAccount.setHint(R.string.signUp_Email_number);
                            mDataBinding.editAccount.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                        } else{
                            //手机找回
                            type=true;
                            mDataBinding.tvType.setText(R.string.forget_email_Mobile);
                            mDataBinding.tvTitle.setText(R.string.forget_phone_Mobile);
                            mDataBinding.editAccount.setHint(R.string.signUp_phone_number);
                            mDataBinding.editAccount.setInputType(InputType.TYPE_CLASS_PHONE);
                        }
                     mDataBinding.selectCountry.setVisibility(type ? View.VISIBLE:View.GONE);

                    }
                });
                break;

            case R.id.select_country:
                startForResult(CountryFragment.newInstance(), ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_COUNTRY);

                break;

            case R.id.get_yzm:

                mEditAccounts = mDataBinding.editAccount.getEditableText().toString().trim();
                mArea = mDataBinding.tvArea.getText().toString().trim();

                if (TextUtils.isEmpty(mEditAccounts)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_account_empty_hint));
                    mDataBinding.editAccount.requestFocus();
                    return;
                }
                if (type){
                    if (mDataBinding.tvArea.getText().toString().equals("+86")&&!StrUtil.check(mEditAccounts, StrUtil.mobileCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_phone_illegal_hint));
                        mDataBinding.editAccount.requestFocus();
                        return ;
                    }

                }else {
                    if (!StrUtil.check(mEditAccounts, StrUtil.emailCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_email_illegal_hint));
                        mDataBinding.editAccount.requestFocus();
                        return ;
                    }
                }


                 YunpianCaptchaUtils.getInstance().start(getActivity());
                break;
            case R.id.btn_next:
                final String editAccount = mDataBinding.editAccount.getEditableText().toString().trim();
                final String  tvArea =mDataBinding.tvArea.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                String Area = tvArea.substring(1, tvArea.length());
                if (TextUtils.isEmpty(editAccount)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_account_empty_hint));
                    mDataBinding.editAccount.requestFocus();
                    return;
                }
                if (type){
                    if (mDataBinding.tvArea.getText().toString().equals("+86")&&!StrUtil.check(editAccount, StrUtil.mobileCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_phone_illegal_hint));
                        mDataBinding.editAccount.requestFocus();
                        return ;
                    }

                }else {
                    if (!StrUtil.check(editAccount, StrUtil.emailCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_email_illegal_hint));
                        mDataBinding.editAccount.requestFocus();
                        return ;
                    }
                }

                if (WonderfulStringUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }
                ExEventBus.getDefault().startFragment(PhoneForgotFragment2.newInstance(editAccount,Area,edCode,type));

                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_COUNTRY
                    ) {
                if (data != null) {
                    final String countrys = data.getString("country");
                    final String Area = data.getString("area");
                    mDataBinding.tvArea.setText(Area);
                    mDataBinding.tvCountry.setText(countrys);
                }

            }
        }
    }

    @Override
    public void onCaptchaSuccess(String msg) {
        mPresenter.phoneForgotCode(mEditAccounts,mArea,msg);
        mDataBinding.getYzm.setEnabled(false);
    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulToastUtils.showToast(msg);

    }
}
