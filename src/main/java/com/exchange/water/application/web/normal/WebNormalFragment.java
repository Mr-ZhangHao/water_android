package com.exchange.water.application.web.normal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

 import com.exchange.water.application.R;
import com.exchange.water.application.databinding.FragmentWebNormalBinding;
import com.exchange.water.application.web.base.BaseWebFragment;

/**
 * Created by lion on 2017/5/4.
 */

public class WebNormalFragment extends BaseWebFragment<WebNormalContract.Presenter,
        FragmentWebNormalBinding> implements
        WebNormalContract.View<WebNormalContract.Presenter> {

    private WebNormalContract.Presenter mPresenter;

    private String mTitle;

    public static WebNormalFragment newInstance(String title, String url) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("url", url);
        WebNormalFragment fragment = new WebNormalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onBind() {
        initData();
        mPresenter = new WebNormalPresenter(getActivity(), this);
        if (!TextUtils.isEmpty(mTitle)) {
            initTitle(mTitle);
        } else {
            initTitle(R.string.find_title_init);
        }
        initWebView(mDataBinding.webContent, mPresenter);
        mDataBinding.webContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setTitle(view.getTitle());
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle data = getArguments();
        if (data != null) {
            mTitle = data.getString("title");
        }
    }

    @Override
    protected WebView getWebView() {
        return mDataBinding.webContent;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_web_normal;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (!onBackPressed()) {
            return super.onBackPressedSupport();
        }
        return true;
    }

    public boolean onBackPressed() {
        if (mDataBinding.webContent.canGoBack()) {
            mDataBinding.webContent.goBack();
            return true;
        }
        return false;
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
    public void setPresenter(WebNormalContract.Presenter presenter) {
        this.mPresenter =presenter;
    }
}
