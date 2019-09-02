package com.talk2tail.di.modules;

import com.talk2tail.ownerdashboard.model.repo.IOwnerDashboardRepo;
import com.talk2tail.ownerdashboard.model.repo.OwnerDashboardRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    public IOwnerDashboardRepo ownerDashboardRepo() {
        return new OwnerDashboardRepo();
    }

}
