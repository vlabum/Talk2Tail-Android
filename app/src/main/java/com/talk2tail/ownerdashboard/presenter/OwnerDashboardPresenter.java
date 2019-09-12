package com.talk2tail.ownerdashboard.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.model.entity.TalkToTailEvent;
import com.talk2tail.ownerdashboard.model.repo.IOwnerDashboardRepo;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import lombok.Getter;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class OwnerDashboardPresenter extends MvpPresenter<OwnerDashboardView> {

    @Inject
    protected Router router;

    @Inject
    protected IOwnerDashboardRepo dashboardRepo;

    private Scheduler mainThreadScheduler;

    final List<DogItemDTO> dogs = new ArrayList<>();

    @Getter
    final List<TalkToTailEvent> events = new ArrayList<>();

    public OwnerDashboardPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        generateTestEvents();
        getViewState().init();
        dogs.addAll(dashboardRepo.getGoodDoggies(5));
        getViewState().initMenu(dogs.size());
        getViewState().addDogs(dogs);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void backClick() {
        router.exit();
    }

    private void generateTestEvents() {
        events.addAll(dashboardRepo.getTestEvents());
    }

    public void showHideClicked(boolean isEnabled) {
        getViewState().clearDogs();
        getViewState().showAllDogs(!isEnabled);
        getViewState().addDogs(dogs);
    }

    public void showHideSearch(boolean searchEnabled) {
        getViewState().showSearch(!searchEnabled);
    }

    public void showHideFilter(boolean filterEnabled) {
        getViewState().showFilter(!filterEnabled);
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

    public void applyFilters(boolean maleChecked, boolean femaleChecked, boolean veteranChecked) {
        final List<DogItemDTO> filteredDogs = new ArrayList<>();
        getViewState().clearDogs();
        getViewState().showAllDogs(true);
        if (!maleChecked && !femaleChecked && !veteranChecked ) {
            filteredDogs.addAll(dogs);
        }
        else {
            for (DogItemDTO dogItemDTO : dogs) {
                if (maleChecked && dogItemDTO.getGender().equals("M")) {
                    filteredDogs.add(dogItemDTO);
                }
                else if (femaleChecked && dogItemDTO.getGender().equals("F")) {
                    filteredDogs.add(dogItemDTO);
                }
                else if (veteranChecked && dogItemDTO.getDogAge() > 8) {
                    filteredDogs.add(dogItemDTO);
                }
            }
        }
        getViewState().addDogs(filteredDogs);
    }
}
