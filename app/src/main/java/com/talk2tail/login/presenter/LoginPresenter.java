package com.talk2tail.login.presenter;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.api.SingleCallbackWrapperLogin;
import com.talk2tail.common.model.entity.api.LoginUser;
import com.talk2tail.common.model.entity.api.LoginUserResponse;
import com.talk2tail.common.model.entity.api.RegisterUser;
import com.talk2tail.common.model.entity.api.RegisterUserResponse;
import com.talk2tail.common.model.entity.dto.DogShort;
import com.talk2tail.common.model.repo.IRepo;
import com.talk2tail.login.view.LoginView;
import com.talk2tail.navigation.Screens;

import java.util.List;

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

    @Inject
    protected AccountManager accountManager;

    public LoginPresenter() {
    }

    public LoginPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Account[] account = accountManager.getAccountsByType(AppConstants.ACCOUNT_TYPE);
        //TODO: сделать выбор аккаунта, если их более чем 1
        /*
        if (account.length == 1) {
            final String token = accountManager.peekAuthToken(account[0], AppConstants.AUTH_TOKEN_TYPE);
            if (TextUtils.isEmpty(token)) {
                final String pwd = accountManager.getPassword(account[0]);
                if (pwd != null) {
                    loginUser(account[0].name, accountManager.getPassword(account[0]));
                }
                return;
            } else {
                repo.setToken(token);
                getCountDogsAndComeIn(repo.getToken());
            }
        }

         */
        if (account.length == 0) {
            getViewState().showErrorMessage("no accounts");
        }
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
        getViewState().showLoading();
        SingleCallbackWrapperLogin<RegisterUserResponse> singleObserver = repo.registerUser(registerUser)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperLogin<RegisterUserResponse>(getViewState()) {
                    @Override
                    public void onSuccess(RegisterUserResponse registerUserResponse) {
                        final String token = registerUserResponse.getKey();
                        getViewState().showToast(registerUserResponse.getKey());
                        updateAccount(email, pwd1, token);
                        repo.setToken(token);
                        getViewState().hideLoading();
                        comeIn(AppConstants.OWNER_DASH_NO_DOG);
                    }
                });
    }

    public void loginUser(String email, String pwd) {
        if ("".equals(email)) { //TODO: этот if - временное явление
            comeIn(AppConstants.OWNER_DASH_ONE_DOG);
        } else {
            LoginUser loginUser = new LoginUser(email, pwd);
            getViewState().showLoading();
            SingleCallbackWrapperLogin<LoginUserResponse> singleObserver = repo.loginUser(loginUser)
                    .observeOn(mainThreadScheduler)
                    .subscribeWith(new SingleCallbackWrapperLogin<LoginUserResponse>(getViewState()) {
                        @Override
                        public void onSuccess(LoginUserResponse loginUserResponse) {
                            final String token = loginUserResponse.getKey();
                            getViewState().showToast(token);
                            updateAccount(email, pwd, token);
                            repo.setToken(token);
                            getViewState().hideLoading();
                            getCountDogsAndComeIn(repo.getToken());
                        }
                    });
        }
    }

    public void getCountDogsAndComeIn(String token) {
        getViewState().showLoading();
        SingleCallbackWrapperLogin<List<DogShort>> dogsObserver = repo.getDogsShort(token)
                .observeOn(mainThreadScheduler)
                .subscribeWith(new SingleCallbackWrapperLogin<List<DogShort>>(getViewState()) {
                    @Override
                    public void onSuccess(List<DogShort> dogShortResponses) {
                        int countDogs = dogShortResponses.size();
                        getViewState().hideLoading();
                        switch (countDogs) {
                            case 0:
                                comeIn(AppConstants.OWNER_DASH_NO_DOG);
                                break;
                            case 1:
                                comeIn(AppConstants.OWNER_DASH_ONE_DOG);
                                break;
                            default:
                                comeIn(AppConstants.OWNER_DASH_A_LOT_OF_DOG);
                        }
                    }
                });
    }

    private void updateAccount(String email, String pwd, String token) {
        final Account[] accs = accountManager.getAccountsByType(AppConstants.ACCOUNT_TYPE);
        for (Account acc : accs) {
            if (acc.name.equals(email)) {
                accountManager.setPassword(acc, pwd);
                accountManager.setAuthToken(acc, AppConstants.AUTH_TOKEN_TYPE, token);
                return;
            }
        }
        final Account account = new Account(email, AppConstants.ACCOUNT_TYPE);
        accountManager.addAccountExplicitly(account, pwd, null);
        accountManager.setAuthToken(account, AppConstants.AUTH_TOKEN_TYPE, token);
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