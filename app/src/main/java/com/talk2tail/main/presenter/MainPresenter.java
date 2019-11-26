package com.talk2tail.main.presenter;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.App;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.api.SingleCallbackWrapperLogin;
import com.talk2tail.common.model.api.SingleCallbackWrapperMain;
import com.talk2tail.common.model.entity.dto.DogShort;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.main.view.MainView;
import com.talk2tail.navigation.Screens;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import lombok.Getter;
import lombok.Setter;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    protected Router router;

    @Inject
    protected IRepo repo;

    private Scheduler mainThreadScheduler;

    AccountManager accountManager;

    public MainPresenter() {
    }

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getCountDogsAndComeIn(String token) {
        SingleCallbackWrapperMain<List<DogShort>> dogsObserver = repo.getDogsShort(token)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperMain<List<DogShort>>(getViewState()) {
                    @Override
                    public void onSuccess(List<DogShort> dogsShortResponse) {
                        repo.setDogsShort(dogsShortResponse);
                        goToOwnerDashboard();
                    }
                });
    }

    public void goToOwnerDashboard() {
        switch (repo.getDogsShort().size()) {
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

    public void goToUserProfileScreen() {
        router.navigateTo(new Screens.UserProfileScreen());
    }
}
