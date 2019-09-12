package com.talk2tail.di.modules;

import android.accounts.AccountManager;

import com.talk2tail.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App app() {
        return this.app;
    }

    @Provides
    public AccountManager getAccountManager() {
        return AccountManager.get(app);
    }

}
