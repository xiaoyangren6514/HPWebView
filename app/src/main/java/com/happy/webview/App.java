package com.happy.webview;

import com.happy.lib_base.BaseApplication;
import com.happy.lib_base.loadsir.CustomCallback;
import com.happy.lib_base.loadsir.EmptyCallback;
import com.happy.lib_base.loadsir.ErrorCallback;
import com.happy.lib_base.loadsir.LoadingCallback;
import com.happy.lib_base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

}
