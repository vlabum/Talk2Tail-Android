package com.talk2tail.userprofile.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface UserProfileView extends MvpView {

    void setPhoto(String url);

    void setName(String userName);

    void setCity(String userCity);

    void setPhone(String userPhone);

    void showMessage(String message);
}
