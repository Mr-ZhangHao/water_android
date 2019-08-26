package com.exchange.water.application.ui.user.bind_account;


import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentBindAccountBinding;
import com.exchange.water.application.entity.AccountSetting;
import com.exchange.water.application.utils.ExEventBus;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindAccountFragment extends BaseTitleFragment<FragmentBindAccountBinding> implements BindAccountContact.View,View.OnClickListener{
    private BindAccountContact.Presenter presenter;

    public static BindAccountFragment newInstance() {
        Bundle args = new Bundle();
        BindAccountFragment fragment = new BindAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(BindAccountContact.Presenter presenter) {
    this.presenter = presenter;
    }

    @Override
    public void getAccountSettingSuccess(AccountSetting obj) {

    }

    @Override
    public void getAccountSettingFail(Integer code, String toastMessage) {

    }

    @Override
    protected void onBind() {
        initTitle(R.string.bind_pay);
        new BindAccountPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.imgAliAdd.setOnClickListener(this);
        mDataBinding.imgBankAdd.setOnClickListener(this);
        mDataBinding.imgWeichatAdd.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bind_account;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.img_ali_add:
                ExEventBus.getDefault().startFragment(BindAliFragment.newInstance());

                break;
            case R.id.img_weichat_add:
                ExEventBus.getDefault().startFragment(BindWeiChatFragment.newInstance());

                break;
            case R.id.img_bank_add:
                ExEventBus.getDefault().startFragment(BindBankFragment.newInstance());

                break;

        }
    }
}
