package com.exchange.water.application.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;


import com.exchange.water.application.R;
import com.exchange.water.application.base.ActivityManage;
import com.exchange.water.application.base.BaseVDBFragment;
import com.exchange.water.application.databinding.FragmentMainBinding;
import com.exchange.water.application.ui.home.AssetFragment;
import com.exchange.water.application.ui.home.HomeFragment;
import com.exchange.water.application.ui.home.MarketFragment;
import com.exchange.water.application.ui.home.MineFragment;
import com.exchange.water.application.ui.home.TradingFragment;
import com.exchange.water.application.utils.ExEventBus;

import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseVDBFragment<FragmentMainBinding> implements
        View.OnClickListener, MainContract.View {

    private static final int PAGER_OFF_SCREEN = 4;
    private static final int TAB_FIRST = 0;
    private static final int TAB_SECOND = 1;
    private static final int TAB_THIRD = 2;
    private static final int TAB_FOURTH = 3;
    private static final int TAB_FIFTH = 4;
    public static final int TYPE_NORMAL_LOGIN = 0;
    public static final int TYPE_FAST_LOGIN = 1;
    private static final String KEY_LOGIN_TYPE = "key_login_type";

    private MainContract.Presenter mPresenter;
    private int                    mLoginType;
    private SupportFragment[]      mFragments;

    public static MainFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(KEY_LOGIN_TYPE, type);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onBind() {
        if (getArguments() != null) {
            mLoginType = getArguments().getInt(KEY_LOGIN_TYPE);
        }
        initView();
        ActivityManage.getInstance().setCurrentActivity(getmActivity());

    }


    @Override
    public void onEvent(ExEventBus.MessageEvent event) {
        if (event != null) {
            switch (event.getType()) {
                case ExEventBus.MessageEvent.EVENT_TYPE_JUMP_TRADE:
                    changePage(TAB_SECOND, true);
                    break;
            }
        }

    }


/*    @Subscribe
    public void onEvent(ExEventBus.MessageFragment event) {
        if (event != null) {
            SupportFragment fragment = event.getFragment();
            if (fragment != null) {
                if (fragment instanceof LoginPWDFragment) {
                    LoginPWDFragment loginFragment = findFragment(LoginPWDFragment.class);
                    if (loginFragment == null) {
                        startWithPop(LoginPWDFragment.newInstance());
                    } else {
                        popTo(LoginPWDFragment.class, false);
                    }
                } else {
                    startForResult(fragment, event.getRequestCode());
                }
            }
        }
    }*/

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        for (int i = 0; i < mFragments.length; i++) {
            mFragments[i].onFragmentResult(requestCode, resultCode, data);
        }
    }

    private void initView() {
        mFragments = new SupportFragment[]{
                HomeFragment.newInstance(),
                MarketFragment.newInstance(),
                TradingFragment.newInstance(),
                AssetFragment.newInstance(),
                MineFragment.newInstance()
        };
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position < mFragments.length) {
                    return mFragments[position];
                }
                return null;
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        };

        mDataBinding.viewPager.setOffscreenPageLimit(PAGER_OFF_SCREEN);
        mDataBinding.viewPager.setAdapter(pagerAdapter);
        mDataBinding.btnIndicatorFirst.setOnClickListener(this);
        mDataBinding.btnIndicatorSecond.setOnClickListener(this);
        mDataBinding.btnIndicatorThird.setOnClickListener(this);
        mDataBinding.btnIndicatorFourth.setOnClickListener(this);
        mDataBinding.btnIndicatorFifth.setOnClickListener(this);
        changePage(TAB_FIRST);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id) {
            case R.id.btnIndicatorThird:
                changePage(TAB_THIRD);

                break;
            case R.id.btnIndicatorSecond:
                changePage(TAB_SECOND);
                break;
            case R.id.btnIndicatorFirst:
                changePage(TAB_FIRST);
                break;
            case R.id.btnIndicatorFourth:
                changePage(TAB_FOURTH);
                break;
            case R.id.btnIndicatorFifth:
               changePage(TAB_FIFTH);

                break;
        }
    }

    private void changePage(int page) {
        changePage(page, true);
    }

    private void changePage(int page, boolean animPage) {
        changePage(page, animPage, true);
    }

    private void changePage(int page, boolean animPage, boolean setIndicatorSelected) {
        mDataBinding.viewPager.setCurrentItem(page, animPage);

        if (!setIndicatorSelected) {
            return;
        }
        switch (page) {
            case TAB_FIRST:
                mDataBinding.btnIndicatorFirst.setSelected(true);
                mDataBinding.btnIndicatorSecond.setSelected(false);
                mDataBinding.btnIndicatorThird.setSelected(false);
                mDataBinding.btnIndicatorFourth.setSelected(false);
                mDataBinding.btnIndicatorFifth.setSelected(false);
                break;
            case TAB_SECOND:
                mDataBinding.btnIndicatorFirst.setSelected(false);
                mDataBinding.btnIndicatorSecond.setSelected(true);
                mDataBinding.btnIndicatorThird.setSelected(false);
                mDataBinding.btnIndicatorFourth.setSelected(false);
                mDataBinding.btnIndicatorFifth.setSelected(false);
                break;
            case TAB_THIRD:
                mDataBinding.btnIndicatorFirst.setSelected(false);
                mDataBinding.btnIndicatorSecond.setSelected(false);
                mDataBinding.btnIndicatorThird.setSelected(true);
                mDataBinding.btnIndicatorFourth.setSelected(false);
                mDataBinding.btnIndicatorFifth.setSelected(false);
                break;
            case TAB_FOURTH:
                mDataBinding.btnIndicatorFirst.setSelected(false);
                mDataBinding.btnIndicatorSecond.setSelected(false);
                mDataBinding.btnIndicatorThird.setSelected(false);
                mDataBinding.btnIndicatorFourth.setSelected(true);
                mDataBinding.btnIndicatorFifth.setSelected(false);
                break;
            case TAB_FIFTH:
                mDataBinding.btnIndicatorFirst.setSelected(false);
                mDataBinding.btnIndicatorSecond.setSelected(false);
                mDataBinding.btnIndicatorThird.setSelected(false);
                mDataBinding.btnIndicatorFourth.setSelected(false);
                mDataBinding.btnIndicatorFifth.setSelected(true);
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ExEventBus.MessageFragment.REQUEST_PERMISSION_EXTERNAL_STORAGE) {
            if (permissions != null && grantResults != null &&
                    permissions.length == grantResults.length) {
                boolean isWrite = false;
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                            grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        isWrite = true;
                        break;
                    }
                }
                boolean isRead = false;
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                            grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        isRead = true;
                        break;
                    }
                }
                boolean install = false;

                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(Manifest.permission.REQUEST_INSTALL_PACKAGES) &&
                            grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        install = true;
                        break;
                    }
                }
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    if (isWrite && isRead&&install) {

                        //下载
                      //  UpdateManager.getInstance().downloadAndInstall();
                    }
                }else {
                    if (isWrite && isRead) {
                        //下载
                     //   UpdateManager.getInstance().downloadAndInstall();
                    }
                }

            }
        }
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void hideLoadingPopup() {

    }

    @Override
    public void displayLoadingPopup() {

    }
}
