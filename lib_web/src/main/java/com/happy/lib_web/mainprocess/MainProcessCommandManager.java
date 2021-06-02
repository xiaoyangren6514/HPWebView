package com.happy.lib_web.mainprocess;

import android.os.RemoteException;

import com.google.gson.Gson;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
import com.happy.lib_web.IWebViewProcessToMainProcessInterface;
import com.happy.lib_web.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandManager extends IWebViewProcessToMainProcessInterface.Stub {

    private static MainProcessCommandManager sInstance;
    private static HashMap<String, Command> mCommands = new HashMap<String, Command>();

    private MainProcessCommandManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    public static MainProcessCommandManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandManager.class) {
                sInstance = new MainProcessCommandManager();
            }
        }
        return sInstance;
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callback) throws RemoteException {
        MainProcessCommandManager.getInstance().exec(commandName, new Gson().fromJson(jsonParams, Map.class), callback);
    }

    public void exec(String commandName, Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        mCommands.get(commandName).execute(params, callback);
    }
}
