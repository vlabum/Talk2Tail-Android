package com.talk2tail.login.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface LoginView extends MvpView {
    void showToast(String response); //TODO: потом убрать

    void showErrorMessage(String message);
}
