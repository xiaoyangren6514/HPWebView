package com.happy.lib_web.command;

import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map params, ICallbackFromMainprocessToWebViewProcessInterface callback);
}
