package com.happy.lib_web.settings;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewDefaultSettings {

    private WebSettings mWebSettings;

    private WebViewDefaultSettings() {

    }

    public static WebViewDefaultSettings getInstance() {
        return new WebViewDefaultSettings();
    }

    public void setSettings(WebView webView){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            webView.enableSlowWholeDocumentDraw();
        }
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setBlockNetworkImage(false);
        mWebSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            mWebSettings.setAllowFileAccessFromFileURLs(false);
            mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setDefaultFontSize(16);
        mWebSettings.setMinimumFontSize(10);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        String appCacheDir = webView.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        mWebSettings.setDatabasePath(appCacheDir);
        mWebSettings.setAppCachePath(appCacheDir);
        mWebSettings.setAppCacheMaxSize(1024*1024*80);
//        mWebSettings.setUserAgentString();
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
//            webView.setwe
//        }
    }
}
