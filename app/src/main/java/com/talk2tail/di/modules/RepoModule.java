package com.talk2tail.di.modules;

import com.talk2tail.common.model.INetworkStatus;
import com.talk2tail.common.model.api.IDataSource;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.common.model.repo.Repo;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, NetworkModule.class})
public class RepoModule {

    @Provides
    IRepo repo(IDataSource dataSource, INetworkStatus networkStatus) {
        return new Repo(dataSource, networkStatus);
    }
}
