package com.exchange.water.application.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.exchange.water.application.R;
import com.gyf.barlibrary.ImmersionBar;


/**
 * Created by Administrator on 2018/1/31.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected View rootView;

    protected ImmersionBar immersionBar;
    protected Window window;


    protected boolean isSetTitle = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isImmersionBarEnabled()) initImmersionBar();
        getDialog().setCanceledOnTouchOutside(true);
        window = getDialog().getWindow();
        initView();
        fillWidget();
        loadData();
    }

    @Override
    public void onStart() {
        super.onStart();
        initLayout();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) immersionBar.destroy();
    }

    protected void initImmersionBar() {
        immersionBar = ImmersionBar.with(this, getDialog());
        immersionBar.navigationBarWithKitkatEnable(true).init();
    }

    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected abstract int getLayoutId();

    protected abstract void initLayout();

    protected abstract void initView();

    protected abstract void fillWidget();

    protected abstract void loadData();
}
