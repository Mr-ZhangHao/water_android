package com.exchange.water.application.ui.user.contact_us;

import android.os.Bundle;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentContactUsBinding;
import com.exchange.water.application.ui.home.MineFragment;

/**
 * Created by Administrator on 2019/8/26.
 */

public class ContactUsFragment extends BaseTitleFragment<FragmentContactUsBinding> {

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.mine_contact_us);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_contact_us;
    }
}
