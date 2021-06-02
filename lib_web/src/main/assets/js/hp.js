var hpjs = {};
hpjs.os = {};
hpjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
hpjs.os.isAndroid = !hpjs.os.isIOS;
hpjs.callbacks = {}

hpjs.callback = function (callbackname, response) {
   var callbackobject = hpjs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete hpjs.callbacks[callbackname];
       }
   }
}

hpjs.takeNativeAction = function(commandname, parameters){
    console.log("hpjs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.hpjs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.hp.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.hp.postMessage(JSON.stringify(request))
    }
}

hpjs.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    hpjs.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.hpjs.os.isAndroid){
        window.hp.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.hp.postMessage(JSON.stringify(request))
    }
}

window.hpjs = hpjs;
