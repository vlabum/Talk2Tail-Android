package com.talk2tail.login.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.login.presenter.LoginPresenter;
import com.talk2tail.login.view.LoginView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @Inject
    protected NavigatorHolder navigatorHolder;
    @InjectPresenter
    LoginPresenter presenter;
    private Navigator navigator = new SupportAppNavigator(this, R.id.login_container);
    private Unbinder unbinder;

    @ProvidePresenter
    protected LoginPresenter createPresenter() {
        final LoginPresenter loginPresenter = new LoginPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(loginPresenter);
        return loginPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        presenter.goToLoginFragment();
    }

    @Override
    public void onBackPressed() {
        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backClick()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showToast(String response) {
        return;
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
