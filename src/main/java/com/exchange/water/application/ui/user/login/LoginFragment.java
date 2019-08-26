package com.exchange.water.application.ui.user.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentLoginBinding;
import com.exchange.water.application.entity.Captcha;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.main.MainActivity;
import com.exchange.water.application.main.MainFragment;
import com.exchange.water.application.ui.user.country.CountryFragment;
import com.exchange.water.application.ui.user.forgot_pwd.PhoneForgotFragment;
import com.exchange.water.application.ui.user.signup.SignUpFragment;
import com.exchange.water.application.utils.AccountSettings;
import com.exchange.water.application.utils.EncryUtils;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulCommonUtils;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.YunpianCaptchaUtils;
import com.exchange.water.application.utils.captcha.CaptchaWindow;
import com.qipeng.capatcha.QPCapatcha;
import com.qipeng.capatcha.QPCaptchaConfig;
import com.qipeng.capatcha.QPCaptchaListener;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.exchange.water.application.main.MainFragment.TYPE_NORMAL_LOGIN;


public class LoginFragment extends BaseVDBFragment<FragmentLoginBinding> implements LoginContract.View, View.OnClickListener,YunpianCaptchaUtils.OnCaptchaWindowListener {

    private boolean type = true;

    private LoginContract.Presenter presenter;


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void onBind() {

        mDataBinding.btnLogin.setOnClickListener(this);
        mDataBinding.selectCountry.setOnClickListener(this);
        mDataBinding.tvType.setOnClickListener(this);
        mDataBinding.tvYzm.setOnClickListener(this);
        mDataBinding.tvGoRegister.setOnClickListener(this);
        mDataBinding.forgotPassword.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        mDataBinding.cancel.setOnClickListener(this);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);

        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mDataBinding.cancel.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.cancel.setLayoutParams(layoutParams);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        new LoginPresenter(Injection.provideTasksRepository(getContext()), this);

        onSetLoginAccount(AccountSettings.getInstance().getLastLoginAccount());

        mDataBinding.edLoginPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String editAccount = mDataBinding.editAccount.getEditableText().toString();
                final String edLoginPwd = mDataBinding.edLoginPwd.getEditableText().toString();
           //     final String edImgCode = mDataBinding.edCode.getEditableText().toString();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.btnLogin.setEnabled(!TextUtils.isEmpty(editAccount)&&!TextUtils.isEmpty(edLoginPwd));

                    }
                });
            }
        });
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void loginFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);

    }



    @Override
    public void loginSuccess(User obj) {
        String   mEditAccount = mDataBinding.editAccount.getEditableText().toString().trim();
        String    mEdLoginPwd = mDataBinding.edLoginPwd.getEditableText().toString().trim();
        MyApplication.getApp().setCurrentUser(null);
        MyApplication.getApp().setLoginStatusChange(true);
        String key = WonderfulCommonUtils.getSerialNumber() + mEditAccount + mEdLoginPwd;
        String md5Key = getMD5(key);
        SharedPreferenceInstance.getInstance().saveToken(EncryUtils.getInstance().encryptString(md5Key, MyApplication.getApp().getPackageName()));
        MyApplication.getApp().setCurrentUser(obj);
        SharedPreferenceInstance.getInstance().saveID(obj.getId());
        SharedPreferenceInstance.getInstance().saveTOKEN(obj.getToken());
        SharedPreferenceInstance.getInstance().saveaToken(EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName()));
        updateUI(new Runnable() {
            @Override
            public void run() {
                start(MainFragment.newInstance(MainFragment.TYPE_NORMAL_LOGIN));
           //     ExEventBus.getDefault().startFragment(MainFragment.newInstance(MainFragment.TYPE_NORMAL_LOGIN));
                mDataBinding.edLoginPwd.setText("");


            }
        },  1000);
    }


    public String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.cancel:
            //    pop();
                ExEventBus.getDefault().startFragment(MainFragment.newInstance(TYPE_NORMAL_LOGIN));

                break;


            case R.id.select_country:
                startForResult(CountryFragment.newInstance(),ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_COUNTRY);

                break;

            case R.id.img_is_invisible:

                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;

            case R.id.tv_type:

                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        if (type){
                            //邮箱登录
                            type=false;
                            mDataBinding.tvType.setText(R.string.login_Phone);
                            mDataBinding.tvTitle.setText(R.string.login_Email);
                            mDataBinding.editAccount.setHint(R.string.signUp_Email_number);
                            mDataBinding.editAccount.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                        } else{
                            //手机登录
                            type=true;
                            mDataBinding.tvType.setText(R.string.login_Email);
                            mDataBinding.tvTitle.setText(R.string.login_Phone);
                            mDataBinding.editAccount.setHint(R.string.signUp_phone_number);
                            mDataBinding.editAccount.setInputType(InputType.TYPE_CLASS_PHONE);

                        }
                    //    mDataBinding.selectCountry.setVisibility(type ? View.VISIBLE:View.GONE);

                    }
                });
                break;
            case R.id.forgot_password:
                ExEventBus.getDefault().startFragment(PhoneForgotFragment.newInstance());

                break;

            case R.id.btn_login:

                final String editAccount = mDataBinding.editAccount.getEditableText().toString();
                final String edLoginPwd = mDataBinding.edLoginPwd.getEditableText().toString();
              //  final String edCode = mDataBinding.edCode.getEditableText().toString().trim();
                final String  tvArea =mDataBinding.tvArea.getText().toString().trim();
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
                if (TextUtils.isEmpty(edLoginPwd)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_pwd_empty_hint));
                    mDataBinding.edLoginPwd.requestFocus();
                    return;
                }
                if (!StrUtil.check(edLoginPwd, StrUtil.pwdCheck)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.login_check));
                    mDataBinding.edLoginPwd.requestFocus();
                    return;
                }
        /*        if (TextUtils.isEmpty(edCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_code_empty_hint));
                    mDataBinding.edCode.requestFocus();
                    return;
                }*/
                /*登录*/

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

 /*            presenter.login(mEditAccount,mEdLoginPwd,"");*/

                break;
            case R.id.tv_go_register:

                ExEventBus.getDefault().startFragment(SignUpFragment.newInstance());

                break;
        }
    }
    public void onSetLoginAccount(final String lastLoginAccount) {
        updateUI(new Runnable() {
            @Override
            public void run() {
                mDataBinding.editAccount.setText(lastLoginAccount);
            }
        });
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

            }else if (requestCode == ExEventBus.MessageFragment.REQUEST_CODE_REGISTER){
                onSetLoginAccount(AccountSettings.getInstance().getLastLoginAccount());
            }

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {
        WonderfulToastUtils.showToast(data.getMsg());
        final String editAccount = mDataBinding.editAccount.getEditableText().toString();
        final String edLoginPwd = mDataBinding.edLoginPwd.getEditableText().toString();
      //  presenter.login(editAccount,edLoginPwd,data.getMsg());
    }

    @Override
    public void onCaptchaFail(String msg) {
        WonderfulToastUtils.showToast(msg);

    }
}
