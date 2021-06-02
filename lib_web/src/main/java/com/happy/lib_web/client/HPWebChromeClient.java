package com.happy.lib_web.client;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.happy.lib_web.WebViewCallBack;

public class HPWebChromeClient extends WebChromeClient {

    private static final String TAG = "HPWebChromeClient";

    private WebViewCallBack callBack;

    public HPWebChromeClient(WebViewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (callBack != null) {
            callBack.onReceivedTitle(title);
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.v(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
