package com.talk2tail.dogdashboard.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.dogdashboard.presenter.DogDashboardPresenter;
import com.talk2tail.dogdashboard.view.DogDashboardView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DogDashboardFragment extends MvpAppCompatFragment implements DogDashboardView, BackButtonListener {

    private View view;
    private Unbinder unbinder;

    @InjectPresenter
    DogDashboardPresenter presenter;

    @BindView(R.id.dog_dashboard_text)
    protected TextView textView;

    public DogDashboardFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new DogDashboardFragment();
    }

    @ProvidePresenter
    protected DogDashboardPresenter createPresenter() {
        final DogDashboardPresenter dogDashboardPresenter = new DogDashboardPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(dogDashboardPresenter);
        return dogDashboardPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dog_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
