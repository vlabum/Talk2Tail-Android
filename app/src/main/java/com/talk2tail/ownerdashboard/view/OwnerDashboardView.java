package com.talk2tail.ownerdashboard.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface OwnerDashboardView extends MvpView {

    void addDogs(List<DogItemDTO> dogs);

    void showAllDogs();

    void hideDogs();

    void clearDogs();

    void initGrid();

}
