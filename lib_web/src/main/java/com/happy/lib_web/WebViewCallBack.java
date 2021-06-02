package com.happy.lib_web;

public interface WebViewCallBack {
    void pageStarted(String url);

    void pageFinished(String url);

    void onReceivedError();

    void onReceivedTitle(String title);
}
