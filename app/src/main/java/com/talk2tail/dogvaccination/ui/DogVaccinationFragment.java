package com.talk2tail.dogvaccination.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.dogvaccination.presenter.DogVaccinationPresenter;
import com.talk2tail.dogvaccination.view.DogVaccinationView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DogVaccinationFragment extends MvpAppCompatFragment implements DogVaccinationView, BackButtonListener {

    private View view;
    private Unbinder unbinder;

    @InjectPresenter
    DogVaccinationPresenter presenter;

    @BindView(R.id.dog_vaccination_text)
    protected TextView textView;

    public DogVaccinationFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new DogVaccinationFragment();
    }

    @ProvidePresenter
    protected DogVaccinationPresenter createPresenter() {
        final DogVaccinationPresenter dogVaccinationPresenter = new DogVaccinationPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(dogVaccinationPresenter);
        return dogVaccinationPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dog_vaccination, container, false);
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
