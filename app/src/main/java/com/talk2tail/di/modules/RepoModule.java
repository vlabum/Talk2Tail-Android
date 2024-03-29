package com.talk2tail.di.modules;

import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.model.api.IDataSource;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.common.model.repo.Repo;
import com.talk2tail.ownerdashboard.model.repo.IOwnerDashboardRepo;
import com.talk2tail.ownerdashboard.model.repo.OwnerDashboardRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, NetworkModule.class})
public class RepoModule {

    @Provides
    @Singleton
    IRepo repo(IDataSource dataSource, INetworkStatus networkStatus) {
        return new Repo(dataSource, networkStatus);
    }

    @Provides
    public IOwnerDashboardRepo ownerDashboardRepo() {
        return new OwnerDashboardRepo();
    }
}
