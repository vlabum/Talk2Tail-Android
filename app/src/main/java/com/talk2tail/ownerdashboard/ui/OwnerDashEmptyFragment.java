package com.talk2tail.ownerdashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class OwnerDashEmptyFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {

    @InjectPresenter
    OwnerDashboardPresenter presenter;
    private View view;
    private Unbinder unbinder;

    public OwnerDashEmptyFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new OwnerDashEmptyFragment();
    }

    @ProvidePresenter
    protected OwnerDashboardPresenter createPresenter() {
        final OwnerDashboardPresenter ownerDashboardPresenter = new OwnerDashboardPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(ownerDashboardPresenter);
        return ownerDashboardPresenter;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_owner_dashboard_empty, container, false);
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

    @Override
    public void addDogs(List<DogItemDTO> dogs) {

    }

    @Override
    public void showAllDogs() {

    }

    @Override
    public void hideDogs() {

    }

    @Override
    public void clearDogs() {

    }

    @Override
    public void initGrid() {

    }

}
