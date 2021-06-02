package com.happy.webview.command;

import android.os.RemoteException;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.happy.lib_base.autoservice.HappyServiceLoader;
import com.happy.lib_common.autoservice.IUserCenterService;
import com.happy.lib_common.eventbus.LoginEvent;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
import com.happy.lib_web.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {

    private static final String TAG = "CommandLogin";

    IUserCenterService iUserCenterService = HappyServiceLoader.load(IUserCenterService.class);
    ICallbackFromMainprocessToWebViewProcessInterface callback;
    String callbackNameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        if (iUserCenterService != null && !iUserCenterService.isLogined()) {
            iUserCenterService.login();
            this.callback = callback;
            this.callbackNameFromNativeJs = String.valueOf(params.get("callbackname"));
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.v(TAG, event.userName);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("accountName", event.userName);
        if (this.callback != null) {
            try {
                this.callback.onResult(callbackNameFromNativeJs, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
