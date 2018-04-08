package com.benjamin.widget.h5;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class H5WebChromeClient extends WebChromeClient {
    private ProgressBar progressbar;
    private H5WebChromeClient.WebViewChangeTitleCallback webViewChangeTitleCallback;
    private String currentUrl;
    private Map<String, String> titles = new HashMap();

    public H5WebChromeClient() {
    }

    public void setProgressbar(ProgressBar progressbar) {
        this.progressbar = progressbar;
    }

    public void setWebViewChangeTitleCallback(H5WebChromeClient.WebViewChangeTitleCallback webViewChangeTitleCallback) {
        this.webViewChangeTitleCallback = webViewChangeTitleCallback;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        if(this.progressbar != null) {
            if(newProgress == 100) {
                this.progressbar.setVisibility(View.GONE);
            } else {
                if(!this.progressbar.isShown()) {
                    this.progressbar.setVisibility(View.VISIBLE);
                }

                this.progressbar.setProgress(newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }
    }

    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if(TextUtils.isEmpty(title)) {
            title = "";
        }

        this.titles.put(this.currentUrl, title);
        String currentTitle = (String)this.titles.get(this.currentUrl);
        if(!TextUtils.isEmpty(currentTitle) && this.webViewChangeTitleCallback != null) {
            this.webViewChangeTitleCallback.onWebViewTitleChange(currentTitle);
        }

    }

    public interface WebViewChangeTitleCallback {
        void onWebViewTitleChange(String var1);
    }
}