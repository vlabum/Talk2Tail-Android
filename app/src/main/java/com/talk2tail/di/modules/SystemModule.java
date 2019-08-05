package com.talk2tail.di.modules;

import com.talk2tail.common.model.ISystemInfo;
import com.talk2tail.common.ui.SystemInfo;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

    @Provides
    public ISystemInfo systemInfo() {
        return new SystemInfo();
    }

}
