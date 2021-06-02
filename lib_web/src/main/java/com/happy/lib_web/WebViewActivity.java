package com.happy.lib_web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.happy.lib_web.databinding.ActivityWebviewBinding;

public class WebViewActivity extends AppCompatActivity {

    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";

    private ActivityWebviewBinding mBinding;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        url = getIntent().getStringExtra(EXTRA_URL);
        if (TextUtils.isEmpty(url)) finish();
        setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        useFragment();
//        WebSettings webViewSettings = mBinding.webView.getSettings();
//        webViewSettings.setJavaScriptEnabled(true);
//        mBinding.webView.loadUrl(url);
    }

    private void useFragment() {
        Fragment fragment = WebViewFragment.newInstance(url, true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_webview, fragment).commit();
    }

    public void updateTitle(String title) {
        setTitle(title);
    }

    public static Intent createIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

}
