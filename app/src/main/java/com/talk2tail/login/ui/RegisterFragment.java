package com.talk2tail.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterFragment extends MvpAppCompatFragment implements LoginView, BackButtonListener {

    @BindView(R.id.loading_rl)
    protected RelativeLayout loadingView;

    @BindView(R.id.reg_email_te)
    protected EditText email;

    @BindView(R.id.reg_pwd_te)
    protected EditText pwd;

    @BindView(R.id.reg_pwdconfirm_te)
    protected EditText confirmPwd;

    @BindView(R.id.reg_ok_btn)
    protected Button regOk;

    @BindView(R.id.reg_login_tv)
    protected TextView toLogin;

    @InjectPresenter
    LoginPresenter presenter;

    private View view;
    private Unbinder unbinder;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @ProvidePresenter
    protected LoginPresenter createPresenter() {
        final LoginPresenter loginPresenter = new LoginPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(loginPresenter);
        return loginPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        regOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegOk();
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
        return view;
    }

    private void onClickLogin() {
        presenter.goToLoginFragment();
    }

    private void onClickRegOk() {
        String sEmail = email.getText().toString();
        String sPwd = pwd.getText().toString();
        String sConfirmPwd = confirmPwd.getText().toString();
        presenter.registerUser(sEmail, sPwd, sConfirmPwd);
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

    @Override
    public void showToast(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

}
