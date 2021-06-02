// IWebViewProcessToMainProcessInterface.aidl
package com.happy.lib_web;
import com.happy.lib_web.ICallbackFromMainprocessToWebViewProcessInterface;
// Declare any non-default types here with import statements

interface IWebViewProcessToMainProcessInterface {
   void handleWebCommand(String commandName,String jsonParams,in ICallbackFromMainprocessToWebViewProcessInterface callback);
}