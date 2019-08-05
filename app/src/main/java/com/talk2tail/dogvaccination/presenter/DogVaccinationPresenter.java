package com.talk2tail.dogvaccination.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.dogvaccination.view.DogVaccinationView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DogVaccinationPresenter extends MvpPresenter<DogVaccinationView> {

    @Inject
    protected Router router;

    private Scheduler mainThreadScheduler;

    public DogVaccinationPresenter(Scheduler mainThreadScheduler) {
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

    public void backClick() {
        router.exit();
    }
}
