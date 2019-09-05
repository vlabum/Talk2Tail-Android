package com.talk2tail.login.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.model.api.RegisterUser;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.login.view.LoginView;
import com.talk2tail.navigation.Screens;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
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

    public void goToChechInFragment() {
        router.navigateTo(new Screens.RegisterFragmentScreen());
    }

    public void registerUser(String email, String pwd1, String pwd2) {
        RegisterUser registerUser = new RegisterUser(email, pwd1, pwd2);
        Disposable d = repo.registerUser(registerUser)
                .observeOn(mainThreadScheduler)
                .subscribe(result -> getViewState().temporaryOut("asdf"));
    }
}