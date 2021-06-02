// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.happy.lib_web;

// Declare any non-default types here with import statements

interface ICallbackFromMainprocessToWebViewProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onResult(String callbackName, String response);
}