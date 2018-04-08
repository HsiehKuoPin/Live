package com.benjamin.widget.h5;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.os.Build.VERSION;
import android.webkit.WebSettings.PluginState;
import com.benjamin.widget.h5.H5WebChromeClient.WebViewChangeTitleCallback;

public class H5WebView extends WebView {
    private H5WebChromeClient h5WebChromeClient;
    private H5WebView.WebViewInterceptUrlCallback webViewInterceptUrlCallback;

    public H5WebView(Context context) {
        super(context);
        this.initView();
    }

    public H5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    public H5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView();
    }

    public void setWebViewChangeTitleCallback(WebViewChangeTitleCallback callback) {
        this.h5WebChromeClient.setWebViewChangeTitleCallback(callback);
    }

    public void setWebChromeClient(WebChromeClient client) {
        if(client instanceof H5WebChromeClient) {
            this.h5WebChromeClient = (H5WebChromeClient)client;
        }

        super.setWebChromeClient(client);
    }

    void initView() {
        this.h5WebChromeClient = new H5WebChromeClient();
        this.getSettings().setUseWideViewPort(true);
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebChromeClient(this.h5WebChromeClient);
        this.getSettings().setPluginState(PluginState.ON);
        this.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.getSettings().setAllowFileAccess(true);
        this.getSettings().setDefaultTextEncodingName("UTF-8");
        this.getSettings().setGeolocationEnabled(true);
        this.getSettings().setLoadWithOverviewMode(true);
        this.getSettings().setDomStorageEnabled(true);
        this.getSettings().setDatabaseEnabled(true);
        this.getSettings().setAppCacheMaxSize(8388608L);
        String appCachePath = this.getContext().getCacheDir().getAbsolutePath();
        this.getSettings().setAppCachePath(appCachePath);
        this.getSettings().setAppCacheEnabled(true);
        if(VERSION.SDK_INT >= 21) {
            this.getSettings().setMixedContentMode(0);
        }

        this.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(H5WebView.this.webViewInterceptUrlCallback != null) {
                    if(!H5WebView.this.webViewInterceptUrlCallback.onInterceptUrl(url)) {
                        view.loadUrl(url);
                    }
                } else {
                    view.loadUrl(url);
                }

                return true;
            }
        });
    }

    public void setProgressbar(ProgressBar progressbar) {
        this.h5WebChromeClient.setProgressbar(progressbar);
    }

    public void setWebViewInterceptUrlCallback(H5WebView.WebViewInterceptUrlCallback callback) {
        this.webViewInterceptUrlCallback = callback;
    }

    public void loadUrl(String url) {
        super.loadUrl(url);
        this.h5WebChromeClient.setCurrentUrl(url);
    }

    public interface WebViewInterceptUrlCallback {
        boolean onInterceptUrl(String var1);
    }
}