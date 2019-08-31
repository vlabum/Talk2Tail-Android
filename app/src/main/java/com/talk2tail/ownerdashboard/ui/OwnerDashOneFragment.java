package com.talk2tail.ownerdashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.common.ui.recyclerevents.EventRecyclerAdapter;
import com.talk2tail.common.ui.recyclerevents.MarginItemDecoration;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class OwnerDashOneFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {

    @InjectPresenter
    OwnerDashboardPresenter presenter;
    private View view;
    private Unbinder unbinder;

    @BindView(R.id.owner_one_grid_layout)
    GridLayout dogGridLayout;

    public OwnerDashOneFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new OwnerDashOneFragment();
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
        view = inflater.inflate(R.layout.fragment_owner_dashboard_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.events_owner_one_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new MarginItemDecoration((int) (getResources().getDimension(R.dimen.rvEventsLeftMargin))));
        EventRecyclerAdapter adapter = new EventRecyclerAdapter(presenter.getEvents());
        recyclerView.setAdapter(adapter);
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
        final DogItemBigView v = new DogItemBigView(getContext());
        App.getInstance().getAppComponent().inject(v);
        v.setName(dogs.get(0).getDogName());
        v.setAge(dogs.get(0).getDogAge() + " лет");
        v.setWeight(dogs.get(0).getWeight() + " кг");
        v.setPhoto(dogs.get(0).getPhotoUrl());
        dogGridLayout.addView(v);
    }
}
