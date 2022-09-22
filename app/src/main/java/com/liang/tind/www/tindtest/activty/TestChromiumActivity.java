package com.liang.tind.www.tindtest.activty;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import androidx.annotation.Nullable;

public class TestChromiumActivity extends BaseActivity {

    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.webview = (WebView) findViewById(R.id.webview);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
//        webview.loadUrl("https://liulanmi.com/labs/core.html");
        webview.loadUrl("http://yujiangshui.github.io/test-webview/");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chromium;
    }

    @Override
    protected void init() {

    }
}
