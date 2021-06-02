package com.happy.lib_web.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.RequiresApi;

import com.happy.lib_base.BaseApplication;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
import com.happy.lib_web.IWebViewProcessToMainProcessInterface;
import com.happy.lib_web.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static WebViewProcessCommandDispatcher mInstance;
    private IWebViewProcessToMainProcessInterface iWebViewProcessToMainProcessInterface;

    private WebViewProcessCommandDispatcher() {

    }

    public static WebViewProcessCommandDispatcher getInstance() {
        if (mInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                mInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return mInstance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.mApplication, MainProcessCommandService.class);
        BaseApplication.mApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebViewProcessToMainProcessInterface = IWebViewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        initAidlConnection();
    }

    public void exec(String commandName, String params, BaseWebView webView) {
        if (iWebViewProcessToMainProcessInterface != null) {
            try {
                iWebViewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {
                        webView.handleCallback(callbackName, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
