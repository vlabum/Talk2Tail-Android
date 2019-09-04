package com.talk2tail.login.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.api.SingleCallbackWrapperLogin;
import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.login.view.LoginView;
import com.talk2tail.navigation.Screens;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    protected Router router;

    @Inject
    protected IRepo repo;

    private Scheduler mainThreadScheduler;

    public LoginPresenter() {
    }

    public LoginPresenter(Scheduler mainThreadScheduler) {
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

    public void goToLoginFragment() {
        router.navigateTo(new Screens.LoginFragmentScreen());
    }

    public void goToMainActivity() {
        router.navigateTo(new Screens.MainActivityScreen());
    }

    public void goToCheckInFragment() {
        router.navigateTo(new Screens.RegisterFragmentScreen());
    }

    public void registerUser(String email, String pwd1, String pwd2) {
        RegisterUser registerUser = new RegisterUser(email, pwd1, pwd2);
        SingleCallbackWrapperLogin<RegisterUserResponse> singleObserver = repo.registerUser(registerUser)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperLogin<RegisterUserResponse>(getViewState()) {
                    @Override
                    public void onSuccess(RegisterUserResponse registerUserResponse) {
                        getViewState().showToast(registerUserResponse.getKey());
                    }
                });
    }

    public void loginUser(String email, String pwd) {
        LoginUser loginUser = new LoginUser(email, pwd);
        SingleCallbackWrapperLogin<LoginUserResponse> singleObserver = repo.loginUser(loginUser)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperLogin<LoginUserResponse>(getViewState()) {
                    @Override
                    public void onSuccess(LoginUserResponse loginUserResponse) {
                        getViewState().showToast(loginUserResponse.getKey());
                        comeIn(AppConstants.OWNER_DASH_ONE_DOG);
                    }
                });
    }

    private void comeIn(int countPets) {
        switch (countPets) {
            case AppConstants.OWNER_DASH_NO_DOG:
                router.navigateTo(new Screens.MainActivityScreenEmpty());
                break;
            case AppConstants.OWNER_DASH_ONE_DOG:
                router.navigateTo(new Screens.MainActivityScreenOne());
                break;
            default:
                router.navigateTo(new Screens.MainActivityScreen());
        }
    }

    private int checkCountPets() {
        return 0;
    }
}