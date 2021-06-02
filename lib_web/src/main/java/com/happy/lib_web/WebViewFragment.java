package com.happy.lib_web;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.happy.lib_base.loadsir.ErrorCallback;
import com.happy.lib_base.loadsir.LoadingCallback;
import com.happy.lib_web.databinding.FragmentWebviewBinding;
import com.happy.lib_web.settings.WebViewDefaultSettings;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_REFRESH = "extra_refresh";
    private FragmentWebviewBinding mBinding;
    private String url;
    private boolean canNativeRefresh;
    private LoadService loadService;
    private boolean isFromError = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(EXTRA_URL);
            canNativeRefresh = bundle.getBoolean(EXTRA_REFRESH);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        loadService = LoadSir.getDefault().register(mBinding.srlRefresh, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                mBinding.webView.reload();
            }
        });
        mBinding.webView.registerWebViewCallBack(this);
        mBinding.srlRefresh.setEnableLoadMore(false);
        mBinding.srlRefresh.setEnableRefresh(canNativeRefresh);
        mBinding.srlRefresh.setOnRefreshListener(this);
        mBinding.webView.loadUrl(url);
        return loadService.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (isFromError) {
            mBinding.srlRefresh.setEnableRefresh(true);
        } else {
            mBinding.srlRefresh.setEnableRefresh(canNativeRefresh);
        }
        if (loadService != null) {
            if (isFromError) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                mBinding.srlRefresh.finishRefresh();
                loadService.showSuccess();
            }
        }
        isFromError = false;
    }

    @Override
    public void onReceivedError() {
        isFromError = true;
        mBinding.srlRefresh.finishRefresh();
        if (loadService != null) {
            loadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void onReceivedTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    public static Fragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL, url);
        bundle.putBoolean(EXTRA_REFRESH, canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mBinding.webView.reload();
    }
}
