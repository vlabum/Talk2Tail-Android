package com.talk2tail;

import android.app.Application;

import com.talk2tail.common.model.db.DatabaseRoom;
import com.talk2tail.di.AppComponent;
import com.talk2tail.di.DaggerAppComponent;
import com.talk2tail.di.modules.AppModule;

import lombok.Getter;
import timber.log.Timber;

public class App extends Application {

    @Getter
    private static App instance;

    @Getter
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        DatabaseRoom.create(getApplicationContext());
    }
}
