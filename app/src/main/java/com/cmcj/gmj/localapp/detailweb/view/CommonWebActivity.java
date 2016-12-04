package com.cmcj.gmj.localapp.detailweb.view;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.component.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BasePresenter;
import com.cmcj.gmj.localapp.databinding.ActivityCommonWebBinding;
import com.cmcj.gmj.localapp.detailweb.presenter.CommonWebPresenter;
import com.cmcj.gmj.localapp.utils.LogUtils;

/**
 * Created by guomaojian on 16/12/4.
 */

public class CommonWebActivity extends BaseDataBindingActivity<CommonWebPresenter> implements ICommonWeb {

    public static final String WEB_URL = "webUrl";
    private ActivityCommonWebBinding mBinding;

    @Override
    public ActivityProxy createActivityProxy() {
        return new ActivityProxy() {
            @Override
            public int getContentLayoutId() {
                return R.layout.activity_common_web;
            }

            @Override
            public BasePresenter createActivityPresenter() {
                return new CommonWebPresenter(CommonWebActivity.this);
            }

            @Override
            public void finishCreateDataBinding(ViewDataBinding viewDataBinding) {
                mBinding = (ActivityCommonWebBinding) viewDataBinding;
                showLoadingPage();
                mBinding.webview.getSettings().setJavaScriptEnabled(true);
                mBinding.webview.loadUrl(getPresenter().getWebUrl());
                mBinding.webview.setWebChromeClient(new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        LogUtils.i("AAA", "onProgressChanged : " + newProgress);
                    }

                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        setActivityTitleInfo(title);
                    }
                });
                mBinding.webview.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        LogUtils.i("AAA", "onPageStarted : " + url);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        showNormalPage();
                        LogUtils.i("AAA", "onPageFinished : " + url);
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        };
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mBinding.webview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            mBinding.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
