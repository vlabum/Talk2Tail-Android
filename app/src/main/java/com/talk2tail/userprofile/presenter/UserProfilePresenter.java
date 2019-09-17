package com.talk2tail.userprofile.presenter;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.api.dto.UserInfoFull;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.userprofile.view.UserProfileView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class UserProfilePresenter extends MvpPresenter<UserProfileView> {

    @Inject
    protected Router router;
    @Inject
    protected IRepo repo;
    @Inject
    protected AccountManager accountManager;
    Disposable disposable;
    private Scheduler mainThreadScheduler;

    public UserProfilePresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getUserInfo();
    }

    private void getUserInfo() {
        //TODO: брать текущий аккаунт из базы или синглтона
        final Account[] account = accountManager.getAccountsByType(AppConstants.ACCOUNT_TYPE);
        final String token = accountManager.peekAuthToken(account[0], AppConstants.AUTH_TYPE);
        disposable = repo.getUserFull(token)
                .observeOn(mainThreadScheduler)
                .subscribe(userInfoFullList -> {
                    final UserInfoFull info = userInfoFullList.get(0);
                    getViewState().setName(info.getFirstName() + " " + info.getLastName());
                    if (info.getCityId() != null) {
                        getViewState().setCity(info.getCityId().toString());    //TODO: получить город по его id
                    }
                    if (info.getPhone() != null) {
                        getViewState().setPhone(info.getPhone());
                    }
                }, throwable -> getViewState().showMessage("User info is unavailable"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void backClick() {
        router.exit();
    }

}
