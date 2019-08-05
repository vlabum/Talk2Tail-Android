package com.talk2tail.di.modules;

import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.ui.NetworkStatus;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    public INetworkStatus networkStatus() {
        return new NetworkStatus();
    }

}
