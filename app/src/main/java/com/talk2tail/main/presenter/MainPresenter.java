package com.talk2tail.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.main.view.MainView;
import com.talk2tail.navigation.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    protected Router router;

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        goToOwnerDashboard();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void goToOwnerDashboard() {
        router.navigateTo(new Screens.OwnerDashboardScreen());
    }

    public void goToDogDashboard() {
        router.navigateTo(new Screens.DogDashboardScreen());
    }

    public void goToDogVaccination() {
        router.navigateTo(new Screens.DogVaccinationScreen());
    }

    public void goToSingleScreen() {
    }

    public void goToMultidogScreen() {
//        router.navigateTo(new Screens.MultidogScreen());
    }
}
