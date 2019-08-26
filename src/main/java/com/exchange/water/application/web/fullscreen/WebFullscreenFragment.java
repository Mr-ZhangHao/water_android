package com.exchange.water.application.web.fullscreen;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.exchange.water.application.R;
import com.exchange.water.application.databinding.FragmentHelpWebBinding;
import com.exchange.water.application.utils.widget.ProgressWebView;
import com.exchange.water.application.web.base.BaseWebFragment;


/**
 * Created by lion on 2017/5/4.
 */

public class WebFullscreenFragment extends BaseWebFragment<
        WebFullscreenContract.Presenter, FragmentHelpWebBinding> implements
        WebFullscreenContract.View, ProgressWebView.OnLoadingListener {

    private WebFullscreenContract.Presenter mPresenter;

    public static WebFullscreenFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        WebFullscreenFragment fragment = new WebFullscreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        setTitleVisible(false);
        initData();
        mPresenter = new WebFullscreenPresenter(getActivity(), this);
        initWebView(mDataBinding.webContent, mPresenter);
        mDataBinding.webContent.setOnLoadingListener(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_help_web;
    }

    @Override
    public View getLayoutBase() {
        return mDataBinding.layoutBase;
    }

    @Override
    public void post(Runnable runnable) {
        updateUI(runnable);
    }

    @Override
    public void onLoading(int progress) {
        if (progress == 100) {
            mDataBinding.layoutLoading.setVisibility(View.GONE);
        }
    }

    public boolean onBackPressed() {
        if (mDataBinding.webContent.canGoBack()) {
            mDataBinding.webContent.goBack();
            return true;
        }
        return false;
    }

    @Override
    protected WebView getWebView() {
        return mDataBinding.webContent;
    }

    @Override
    public void setPresenter(WebFullscreenContract.Presenter presenter) {
        this.mPresenter =presenter;
    }
}
