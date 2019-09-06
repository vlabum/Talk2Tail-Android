package com.talk2tail.dogdashboard.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.dogdashboard.view.DogDashboardView;
import com.talk2tail.ownerdashboard.model.repo.IOwnerDashboardRepo;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import lombok.Getter;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DogDashboardPresenter extends MvpPresenter<DogDashboardView> {

    @Inject
    protected Router router;

    @Inject
    protected IOwnerDashboardRepo dashboardRepo;

    private Scheduler mainThreadScheduler;

    final List<DogItemDTO> dogs = new ArrayList<>();

    @Getter
    final List<TalkToTailEvent> events = new ArrayList<>();

    public DogDashboardPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();


        dogs.addAll(dashboardRepo.getGoodDoggies(1));
        generateTestEvents();
        getViewState().addDogs(dogs);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void backClick() {
        router.exit();
    }

    public void searchDogs(String query) {
        final List<DogItemDTO> searchedDogs = new ArrayList<>();
        getViewState().clearDogs();
        getViewState().showAllDogs(true);
        if (query.isEmpty()) {
            searchedDogs.addAll(dogs);
        }
        else {
            for (DogItemDTO dogItemDTO : dogs) {
                if (dogItemDTO.getDogName().toLowerCase().contains(query.toLowerCase())) {
                    searchedDogs.add(dogItemDTO);
                }
            }
        }
        getViewState().addDogs(searchedDogs);
    }

    private void generateTestEvents() {
        events.addAll(dashboardRepo.getTestEvents());
    }
}
