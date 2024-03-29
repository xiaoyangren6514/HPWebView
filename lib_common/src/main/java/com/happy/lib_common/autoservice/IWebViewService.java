package com.happy.lib_common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title);
    Fragment getWebViewFragment(String url);
    void startDemoHtml(Context context);
}
