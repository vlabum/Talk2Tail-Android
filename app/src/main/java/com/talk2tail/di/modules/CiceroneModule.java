package com.talk2tail.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class CiceroneModule {

    @Singleton
    @Provides
    public Cicerone<Router> cicerone() {
        return Cicerone.create();
    }

    @Singleton
    @Provides
    public NavigatorHolder navigatorHolder(Cicerone<Router> cicerone) {
        return cicerone.getNavigatorHolder();
    }

    @Singleton
    @Provides
    public Router router(Cicerone<Router> cicerone) {
        return cicerone.getRouter();
    }

}
