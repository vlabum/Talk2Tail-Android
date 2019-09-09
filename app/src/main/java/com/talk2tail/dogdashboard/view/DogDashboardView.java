package com.talk2tail.dogdashboard.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.talk2tail.common.model.DogWeighData;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface DogDashboardView extends MvpView {
    void addDogs(List<DogItemDTO> dogs);

    void clearDogs();

    void showAllDogs(boolean isShow);

    void addDogWeighData(DogWeighData dogWeighData);
//
//    void showSearch(boolean isShow);
//
//    void showFilter(boolean isShow);
}
