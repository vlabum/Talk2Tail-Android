package com.talk2tail.common.ui;

import android.os.Build;

import com.talk2tail.common.model.ISystemInfo;

public class SystemInfo implements ISystemInfo {

    @Override
    public int getBuildVersion() {
        return Build.VERSION.SDK_INT;
    }

}
