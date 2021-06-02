package com.happy.webview.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.happy.lib_base.BaseApplication;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
import com.happy.lib_web.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.mApplication, String.valueOf(params.get("message")), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
