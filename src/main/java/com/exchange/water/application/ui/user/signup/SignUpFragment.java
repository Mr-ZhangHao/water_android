package com.exchange.water.application.ui.user.signup;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentSignupBinding;
import com.exchange.water.application.ui.user.country.CountryFragment;
import com.exchange.water.application.utils.AccountSettings;
import com.exchange.water.application.utils.CodeUtils;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StatusBarUtils;
import com.exchange.water.application.utils.StrUtil;
import com.exchange.water.application.utils.WonderfulDpPxUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulStringUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;

/**
 * Created by Administrator on 2019/8/21.
 */

public class SignUpFragment extends BaseVDBFragment<FragmentSignupBinding> implements View.OnClickListener {

    private CodeUtils codeUtils;
    private String imgcode;
    private boolean type = true;

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        mDataBinding.cancel.setOnClickListener(this);
        mDataBinding.selectCountry.setOnClickListener(this);
        mDataBinding.btnNext.setOnClickListener(this);
        mDataBinding.imgYzm.setOnClickListener(this);
        mDataBinding.tvType.setOnClickListener(this);
        mDataBinding.tvGoLogin.setOnClickListener(this);

        int statusBarHeight = WonderfulDpPxUtils.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mDataBinding.cancel.getLayoutParams();
        layoutParams.height = statusBarHeight;
        mDataBinding.cancel.setLayoutParams(layoutParams);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
/*

        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        mDataBinding.imgYzm.setImageBitmap(bitmap);
        imgcode = codeUtils.getCode().toLowerCase();

*/


        mDataBinding.editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String editAccount = mDataBinding.editAccount.getEditableText().toString();
                final String edImgCode = mDataBinding.edImgCode.getEditableText().toString();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                      //  mDataBinding.btnNext.setEnabled(!TextUtils.isEmpty(editAccount)&&!TextUtils.isEmpty(edImgCode));
                        mDataBinding.btnNext.setEnabled(!TextUtils.isEmpty(editAccount));
                    }
                });
            }
        });



    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_signup;
    }


    @Override
    public void onClick(final View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.cancel:
                    pop();
                break;
            case R.id.select_country:

                startForResult(CountryFragment.newInstance(),ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_COUNTRY);

                break;

/*            case R.id.img_yzm:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                mDataBinding.imgYzm.setImageBitmap(bitmap);
                imgcode = codeUtils.getCode().toLowerCase();
                break;*/
            case R.id.tv_go_login:
             //   ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
                pop();
                break;
            case R.id.tv_type:
            updateUI(new Runnable() {
                @Override
                public void run() {
                    if (type){
                        //邮箱注册
                        type=false;
                        mDataBinding.tvType.setText(R.string.signUp_Phone);
                        mDataBinding.tvTitle.setText(R.string.signUp_Email);
                        mDataBinding.editAccount.setHint(R.string.signUp_Email_number);
                        mDataBinding.editAccount.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                    } else{
                        //手机注册
                        type=true;
                        mDataBinding.tvType.setText(R.string.signUp_Email);
                        mDataBinding.tvTitle.setText(R.string.signUp_Phone);
                        mDataBinding.editAccount.setHint(R.string.signUp_phone_number);
                        mDataBinding.editAccount.setInputType(InputType.TYPE_CLASS_PHONE);

                    }
                    mDataBinding.selectCountry.setVisibility(type ? View.VISIBLE:View.GONE);

                }
            });
                    break;

            case R.id.btn_next:
                final String editAccount = mDataBinding.editAccount.getEditableText().toString().trim();
                final String  tvArea =mDataBinding.tvArea.getText().toString().trim();
                final String  ImgCode =mDataBinding.edImgCode.getText().toString().trim();
                String Area = tvArea.substring(1, tvArea.length());

                WonderfulLogUtils.logi("Area=========",Area);

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

        /*        if (WonderfulStringUtils.isEmpty(ImgCode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_imgcode_empty_hint));
                    mDataBinding.edImgCode.requestFocus();
                    return;
                }
                if (!ImgCode.toLowerCase().equals(imgcode)) {
                    WonderfulToastUtils.showToast(getResources().getString(R.string.signUp_imgcode_illegal_hint));
                    mDataBinding.edImgCode.requestFocus();
                    return;
                }*/

                ExEventBus.getDefault().startFragment(SignUpFragment2.newInstance(editAccount,Area,type));

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

            }/*else if (requestCode == ExEventBus.MessageFragment.REQUEST_CODE_SELECTE_OUT_LOGIN){
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                mDataBinding.imgYzm.setImageBitmap(bitmap);
                imgcode = codeUtils.getCode().toLowerCase();
            }*/

        }
    }
}
