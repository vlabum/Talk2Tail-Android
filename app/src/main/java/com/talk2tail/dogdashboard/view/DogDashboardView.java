package com.talk2tail.dogdashboard.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface DogDashboardView extends MvpView {
    void addDogs(List<DogItemDTO> dogs);

    void clearDogs();

    void showAllDogs(boolean isShow);
//
//    void showSearch(boolean isShow);
//
//    void showFilter(boolean isShow);
}
