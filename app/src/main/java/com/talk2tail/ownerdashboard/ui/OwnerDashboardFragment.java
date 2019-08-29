package com.talk2tail.ownerdashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class OwnerDashboardFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {

    private View view;
    private Unbinder unbinder;

    @InjectPresenter
    OwnerDashboardPresenter presenter;

    @BindView(R.id.dog_grid_layout)
    GridLayout dogGidLayout;

    public OwnerDashboardFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new OwnerDashboardFragment();
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
        view = inflater.inflate(R.layout.fragment_owner_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void addDogs(List<DogItemDTO> dogs) {
        for (int i = 0; i < dogs.size(); i++) {
            final DogItemView v = new DogItemView(getContext(), i);
            App.getInstance().getAppComponent().inject(v);
            v.setName(dogs.get(i).getDogName());
            v.setAge(dogs.get(i).getDogAge() +" лет");
            v.setWeight(dogs.get(i).getWeight() +" кг");
            v.setPhoto(dogs.get(i).getPhotoUrl());
            dogGidLayout.addView(v);
        }
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
