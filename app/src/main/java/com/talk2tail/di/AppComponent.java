package com.talk2tail.di;

import com.talk2tail.di.modules.AppModule;
import com.talk2tail.di.modules.CiceroneModule;
import com.talk2tail.di.modules.ImageModule;
import com.talk2tail.dogdashboard.presenter.DogDashboardPresenter;
import com.talk2tail.dogvaccination.presenter.DogVaccinationPresenter;
import com.talk2tail.main.presenter.MainPresenter;
import com.talk2tail.main.ui.MainActivity;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ImageModule.class, CiceroneModule.class})
public interface AppComponent {

    void inject(DogDashboardPresenter dogDashboardPresenter);

    void inject(OwnerDashboardPresenter ownerDashboardPresenter);

    void inject(DogVaccinationPresenter dogVaccinationPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(MainActivity mainActivity);
}
