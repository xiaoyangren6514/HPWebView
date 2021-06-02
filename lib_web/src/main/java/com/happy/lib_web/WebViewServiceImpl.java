package com.happy.lib_web;

import android.content.Context;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.happy.lib_common.autoservice.IWebViewService;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title) {
        if (context != null && !TextUtils.isEmpty(url)) {
            context.startActivity(WebViewActivity.createIntent(context, url, title));
        }
    }

    @Override
    public Fragment getWebViewFragment(String url) {
        return WebViewFragment.newInstance(url, true);
    }

    @Override
    public void startDemoHtml(Context context) {
        if (context != null) {
            context.startActivity(WebViewActivity.createIntent(context, "file:///android_asset/demo.html", ""));
        }
    }
}
