package com.happy.lib_web.webviewprocess;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.happy.lib_web.WebViewCallBack;
import com.happy.lib_web.bean.JsBean;
import com.happy.lib_web.client.HPWebChromeClient;
import com.happy.lib_web.client.HPWebViewClient;
import com.happy.lib_web.settings.WebViewDefaultSettings;

public class BaseWebView extends WebView {

    private static final String TAG = "BaseWebView";

    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "hp");
    }

    public void registerWebViewCallBack(WebViewCallBack callBack) {
        setWebChromeClient(new HPWebChromeClient(callBack));
        setWebViewClient(new HPWebViewClient(callBack));
    }

    @JavascriptInterface
    public void takeNativeAction(String jsParams) {
        try {
            Log.d(TAG, jsParams);
            if (!TextUtils.isEmpty(jsParams)) {
                JsBean jsBean = new Gson().fromJson(jsParams, JsBean.class);
                if (jsBean != null) {
//                    if ("showToast".equalsIgnoreCase(jsBean.name)) {
//                        Toast.makeText(getContext(), String.valueOf(new Gson().fromJson(jsBean.param.toString(), Map.class).get("message")), Toast.LENGTH_SHORT).show();
//                    } else {
                    WebViewProcessCommandDispatcher.getInstance().exec(jsBean.name, new Gson().toJson(jsBean.param), this);
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void handleCallback(final String callbackName, final String response) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            post(() -> {
                String jscode = "javascript:hpjs.callback('" + callbackName + "'," + response + ")";
                evaluateJavascript(jscode, null);
            });
        }
    }
}
