package com.talk2tail.ownerdashboard.ui;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.material.button.MaterialButton;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class OwnerDashboardFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {
    private static final int VERT_ROW_COUNT = 3;
    private static final int HOR_ROW_COUNT = 4;

    private View view;
    private Unbinder unbinder;
    private int columnsCount;

    private boolean allDogsEnabled = false;
    private boolean searchEnabled = false;
    private boolean filterEnabled = false;

    @InjectPresenter
    OwnerDashboardPresenter presenter;

    @BindView(R.id.dog_grid_layout)
    GridLayout dogGridLayout;

    @BindView(R.id.dog_menu_layout)
    LinearLayout menuLayout;
    @BindView(R.id.dog_search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.dog_search_view)
    SearchView searchView;

    @BindView(R.id.dog_search_btn)
    MaterialButton searchButton;
    @BindView(R.id.dog_filter_btn)
    MaterialButton filterButton;
    @BindView(R.id.dog_show_all_btn)
    MaterialButton showButton;

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
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            columnsCount = HOR_ROW_COUNT;
        }
        else {
            columnsCount = VERT_ROW_COUNT;
        }
        return view;
    }

    @Override
    public void addDogs(List<DogItemDTO> dogs) {
        final int diffs = columnsCount - dogs.size();
        if (!allDogsEnabled) {
            if (diffs < 0) {    //Собак больше, чем столбцов в строке, то обрезаем
                dogs = dogs.subList(0, columnsCount);
            }
        }

        for (int i = 0; i < dogs.size(); i++) {
            final DogItemView v = new DogItemView(getContext(), i);
            App.getInstance().getAppComponent().inject(v);
            v.setName(dogs.get(i).getDogName());
            v.setAge(dogs.get(i).getDogAge() +" лет");
            v.setWeight(dogs.get(i).getWeight() +" кг");
            v.setPhoto(dogs.get(i).getPhotoUrl());
            dogGridLayout.addView(v);
        }

        fillEmptyCells(diffs);  //если собак меньше, чем столбцов - нужно вкостылить пустые вьюшки

        dogGridLayout.animate().alpha(1.0f).setDuration(500);
    }

    @Override
    public void clearDogs() {
        dogGridLayout.setAlpha(0);
        dogGridLayout.removeAllViews();
    }

    @Override
    public void showAllDogs(boolean isShow) {
        showButton.setIcon(getResources().getDrawable( isShow ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down));
        showButton.setIconTint(ColorStateList.valueOf(getResources().getColor( isShow ? R.color.menuItemEnabledTextColor : R.color.menuItemDisabledTextColor)));
        showButton.setTextColor(getResources().getColor( isShow ? R.color.menuItemEnabledTextColor : R.color.menuItemDisabledTextColor));
        allDogsEnabled = isShow;
    }

    @Override
    public void showSearch(boolean isShow) {
        searchButton.setTextColor(getResources().getColor( isShow ? R.color.menuItemEnabledTextColor : R.color.menuItemDisabledTextColor));
        ObjectAnimator.ofFloat(searchLayout, "translationY", isShow ? searchLayout.getHeight() : -searchLayout.getHeight()).start();
        searchEnabled = isShow;
    }

    @Override
    public void showFilter(boolean isShow) {

    }

    @Override
    public void init() {
        initGrid();
        initSearchView();
    }

    @Override
    public void initMenu(int count) {
        menuLayout.setVisibility(count > columnsCount ? View.VISIBLE : View.GONE);
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

    @OnClick(R.id.dog_show_all_btn)
    public void showAllClicked() {
        presenter.showHideClicked(allDogsEnabled);
    }

    @OnClick(R.id.dog_search_btn)
    public void searchClicked() {
        presenter.showHideSearch(searchEnabled);
    }

    @OnClick(R.id.dog_filter_btn)
    public void filterClicked() {
        presenter.showHideFilter(filterEnabled);
    }

    private void initGrid() {
        dogGridLayout.setColumnCount(columnsCount);
    }

    private void initSearchView() {
        searchView.setOnClickListener(v -> presenter.searchDogs(searchView.getQuery().toString()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchDogs(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    presenter.searchDogs(newText);
                }
                return false;
            }
        });
    }

    private void fillEmptyCells(int emptyCellsCount) {
        while (emptyCellsCount-- > 0) {
            final LinearLayout vi = new LinearLayout(getContext());
            final GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            int marginInDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, getResources()
                            .getDisplayMetrics());
            params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
            vi.setLayoutParams(params);
            vi.setVisibility(View.INVISIBLE);
            dogGridLayout.addView(vi);
        }
    }

}
