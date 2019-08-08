package com.talk2tail.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.login.presenter.LoginPresenter;
import com.talk2tail.login.view.LoginView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginFragment extends MvpAppCompatFragment implements LoginView, BackButtonListener {

    @BindView(R.id.login_te)
    protected EditText login;

    @BindView(R.id.login_pwd_te)
    protected EditText pwd;

    @BindView(R.id.login_ok_btn)
    protected Button loginOk;

    @BindView(R.id.remind_text_tv)
    protected TextView remind;

    @BindView(R.id.checkin_text_tv)
    protected TextView checkIn;

    @InjectPresenter
    LoginPresenter presenter;

    private View view;
    private Unbinder unbinder;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @ProvidePresenter
    protected LoginPresenter createPresenter() {
        final LoginPresenter loginPresenter = new LoginPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(loginPresenter);
        return loginPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        loginOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoginOk();
            }
        });

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCheckIn();
            }
        });
        return view;
    }

    private void onClickCheckIn() {
        presenter.goToChechInFragment();
    }

    private void onClickLoginOk() {
        //TODO: check login
        presenter.goToMainActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Boolean backClick() {
        presenter.backClick();
        return true;
    }

}
