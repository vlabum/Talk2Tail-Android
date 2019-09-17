package com.talk2tail.di;

import com.talk2tail.di.modules.AppModule;
import com.talk2tail.di.modules.CiceroneModule;
import com.talk2tail.di.modules.ImageModule;
import com.talk2tail.di.modules.RepoModule;
import com.talk2tail.dogdashboard.presenter.DogAddPresenter;
import com.talk2tail.dogdashboard.presenter.DogDashboardPresenter;
import com.talk2tail.dogvaccination.presenter.DogVaccinationPresenter;
import com.talk2tail.login.presenter.LoginPresenter;
import com.talk2tail.login.ui.LoginActivity;
import com.talk2tail.main.presenter.MainPresenter;
import com.talk2tail.main.ui.MainActivity;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;
import com.talk2tail.ownerdashboard.ui.DogItemBigView;
import com.talk2tail.ownerdashboard.ui.DogItemView;
import com.talk2tail.userprofile.presenter.UserProfilePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ImageModule.class, CiceroneModule.class, RepoModule.class})
public interface AppComponent {

    void inject(DogDashboardPresenter dogDashboardPresenter);

    void inject(DogAddPresenter dogAddPresenter);

    void inject(OwnerDashboardPresenter ownerDashboardPresenter);

    void inject(DogVaccinationPresenter dogVaccinationPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(DogItemView v);

    void inject(DogItemBigView v);

    void inject(UserProfilePresenter userProfilePresenter);
}
