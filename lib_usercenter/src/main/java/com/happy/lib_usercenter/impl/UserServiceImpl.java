package com.happy.lib_usercenter.impl;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.happy.lib_base.BaseApplication;
import com.happy.lib_common.autoservice.IUserCenterService;
import com.happy.lib_usercenter.LoginActivity;

@AutoService({IUserCenterService.class})
public class UserServiceImpl implements IUserCenterService {

    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.mApplication, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.mApplication.startActivity(intent);
    }
}
