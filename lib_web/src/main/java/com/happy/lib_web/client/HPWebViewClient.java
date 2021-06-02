package com.happy.lib_web.client;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.happy.lib_web.WebViewCallBack;

public class HPWebViewClient extends WebViewClient {

    private WebViewCallBack callBack;

    public HPWebViewClient(WebViewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (callBack != null) {
            callBack.pageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (callBack != null) {
            callBack.pageFinished(url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (callBack != null) {
            callBack.onReceivedError();
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);

    }
}
