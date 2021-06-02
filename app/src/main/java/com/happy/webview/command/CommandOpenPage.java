package com.happy.webview.command;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.happy.lib_base.BaseApplication;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
import com.happy.lib_web.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        String targetClass = String.valueOf(params.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.mApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.mApplication.startActivity(intent);
        }
    }
}
