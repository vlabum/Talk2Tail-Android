package com.talk2tail.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.AppConstants;
import com.talk2tail.main.view.MainView;
import com.talk2tail.navigation.Screens;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    protected Router router;

    @Getter
    @Setter
    private int countPets;

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
        switch (countPets) {
            case AppConstants.OWNER_DASH_NO_DOG:
                goToOwnerDashEmpty();
                break;
            case AppConstants.OWNER_DASH_ONE_DOG:
                goToOwnerDashOne();
                break;
            default:
                goToMultiDogScreen();

        }
    }

    public void goToOwnerDashEmpty() {
        router.navigateTo(new Screens.OwnerDashEmptyFragmentScreen());
    }

    public void goToOwnerDashOne() {
        router.navigateTo(new Screens.OwnerDashOneFragmentScreen());
    }

    public void goToDogDashboard() {
        router.navigateTo(new Screens.DogDashboardScreen());
    }

    public void goToDogVaccination() {
        router.navigateTo(new Screens.DogVaccinationScreen());
    }

    public void goToMultiDogScreen() {
        router.navigateTo(new Screens.OwnerDashboardScreen());
    }

    public void goToDogAddScreen() {
        router.navigateTo(new Screens.DogAddScreen());
    }

    public void onFabClick() {
        Timber.d("FAB clicked");
    }

}
