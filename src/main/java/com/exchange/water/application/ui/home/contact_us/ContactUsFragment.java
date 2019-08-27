package com.exchange.water.application.ui.home.contact_us;

import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentContactUsBinding;
import com.exchange.water.application.utils.WonderfulCommonUtils;

/**
 * Created by Administrator on 2019/8/26.
 */

public class ContactUsFragment extends BaseTitleFragment<FragmentContactUsBinding>implements View.OnClickListener {

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.mine_contact_us);

        mDataBinding.llEmail.setOnClickListener(this);
        mDataBinding.llWeichat.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_contact_us;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_email:
                WonderfulCommonUtils.copyText(getContext(),mDataBinding.tvEmail.getText().toString());
                break;
            case R.id.ll_weichat:

                WonderfulCommonUtils.copyText(getContext(),mDataBinding.tvWeichat.getText().toString());
                break;

        }
    }

}
