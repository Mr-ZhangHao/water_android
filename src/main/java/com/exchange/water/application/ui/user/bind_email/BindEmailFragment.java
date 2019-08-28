package com.exchange.water.application.ui.user.bind_email;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentBindEmailBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.ui.user.country.CountryFragment;
import com.exchange.water.application.ui.user.login.LoginContract;
import com.exchange.water.application.ui.user.login.LoginPresenter;
import com.exchange.water.application.utils.AccountSettings;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class BindEmailFragment extends BaseTitleFragment<FragmentBindEmailBinding> implements BindEmailContract.View ,YunpianCaptchaUtils.OnCaptchaWindowListener{


    private BindEmailContract.Presenter presenter;
    private String mArea;


    public static BindEmailFragment newInstance() {
        Bundle args = new Bundle();
        BindEmailFragment fragment = new BindEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.Security_center_modify_bingd_email);

         if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
             initTitle(R.string.Security_center_modify_bingd_email);
             mDataBinding.editEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
             mDataBinding.editEmail.setHint(getContext().getResources().getString(R.string.bind_email));
             mDataBinding.selectCountry.setVisibility(View.GONE);
        }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
             initTitle(R.string.Security_center_modify_bingd_phone);
             mDataBinding.editEmail.setHint(getContext().getResources().getString(R.string.bind_phone));

             mDataBinding.editEmail.setInputType(InputType.TYPE_CLASS_PHONE);
             mDataBinding.selectCountry.setVisibility(View.VISIBLE);
         }
        YunpianCaptchaUtils.getInstance().setCaptchaWindowListener(this);
        new BindEmailPresenter(Injection.provideTasksRepository(getContext()), this);

        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);
        mDataBinding.selectCountry.setOnClickListener(this);
        mDataBinding.edCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String  editEmail =mDataBinding.editEmail.getText().toString().trim();
                final String  edCode =mDataBinding.edCode.getText().toString().trim();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnConfirm.setEnabled(!TextUtils.isEmpty(editEmail)&&!TextUtils.isEmpty(edCode));
                    }
                });
            }
        });

    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.select_country:

                startForResult(CountryFragment.newInstance(), ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_COUNTRY);

                break;
            case R.id.tv_yzm:
                final String  editAccounts =mDataBinding.editEmail.getEditableText().toString().trim();
                if (TextUtils.isEmpty(editAccounts)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_account_empty_hint));
                    mDataBinding.editEmail.requestFocus();
                    return;
                }
                YunpianCaptchaUtils.getInstance().start(getActivity());
                break;
            case R.id.btn_confirm:

                final String  editAccount =mDataBinding.editEmail.getEditableText().toString().trim();
                final String  edCode =mDataBinding.edCode.getEditableText().toString().trim();
                final String  Area =mDataBinding.tvArea.getText().toString().trim();
                mArea = Area.substring(1, Area.length());

                if (TextUtils.isEmpty(editAccount)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_account_empty_hint));
                    mDataBinding.editEmail.requestFocus();
                    return;
                }
                if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
                    if (!StrUtil.check(editAccount, StrUtil.emailCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_email_illegal_hint));
                        mDataBinding.editEmail.requestFocus();
                        return ;
                    }
                }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){

                    if (mDataBinding.tvArea.getText().toString().equals("+86")&&!StrUtil.check(editAccount, StrUtil.mobileCheck)) {
                        WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_phone_illegal_hint));
                        mDataBinding.editEmail.requestFocus();
                        return ;
                    }
                }

                if (TextUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }

                if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
                   presenter.bindEmail(MyApplication.getApp().getCurrentUser().getToken(),editAccount,edCode);
                }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
                    presenter.bindPhone(MyApplication.getApp().getCurrentUser().getToken(),editAccount,edCode, mArea);
                }

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
    protected int getLayoutRes() {
        return R.layout.fragment_bind_email;
    }

    @Override
    public void setPresenter(BindEmailContract.Presenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void bindEmailSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        updateUI(new Runnable() {
            @Override
            public void run() {
                MyApplication.getApp().getCurrentUser().setM_email(mDataBinding.editEmail.getEditableText().toString().trim());
                setFragmentResult(RESULT_OK, null);
                pop();
            }
        },1500);
    }

    @Override
    public void bindEmailFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);

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
    public void sendEmailSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    @Override
    public void sendEmailCodeFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);
        mDataBinding.tvYzm.setEnabled(true);

    }

    @Override
    public void bindPhoneSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        WonderfulToastUtils.showToast(obj);
        updateUI(new Runnable() {
            @Override
            public void run() {
                MyApplication.getApp().getCurrentUser().setM_name(mDataBinding.editEmail.getEditableText().toString().trim());
                setFragmentResult(RESULT_OK, null);
                pop();
            }
        },1500);
    }

    @Override
    public void bindPhoneFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, toastMessage);

    }


    @Override
    public void onCaptchaSuccess(String data) {
        final String  Area =mDataBinding.tvArea.getText().toString().trim();
        final String   mArea = Area.substring(1, Area.length());
        final String  editAccount =mDataBinding.editEmail.getEditableText().toString().trim();
        if (MyApplication.getApp().getCurrentUser().getM_name()!=null&&!MyApplication.getApp().getCurrentUser().getM_name().isEmpty()){
            presenter.sendEmailCode(editAccount,data);

        }else if (MyApplication.getApp().getCurrentUser().getM_email()!=null&&!MyApplication.getApp().getCurrentUser().getM_email().isEmpty()){
            presenter.sendCode(editAccount,mArea,data);

        }

        mDataBinding.tvYzm.setEnabled(false);

    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulCodeUtils.checkedErrorCode(this, -1, msg);
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
