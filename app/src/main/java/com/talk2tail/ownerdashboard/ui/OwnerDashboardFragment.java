package com.talk2tail.ownerdashboard.ui;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.material.button.MaterialButton;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.common.ui.recyclerevents.EventRecyclerAdapter;
import com.talk2tail.common.ui.recyclerevents.MarginItemDecoration;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
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
    @BindView(R.id.dog_filter_layout)
    LinearLayout filterLayout;
    @BindView(R.id.dog_search_view)
    SearchView searchView;

    @BindView(R.id.dog_search_btn)
    MaterialButton searchButton;
    @BindView(R.id.dog_filter_btn)
    MaterialButton filterButton;
    @BindView(R.id.dog_show_all_btn)
    MaterialButton showButton;

    @BindView(R.id.check_dogs_male)
    CheckBox maleCheckbox;
    @BindView(R.id.check_dogs_female)
    CheckBox femaleCheckbox;
    @BindView(R.id.check_dogs_veteran)
    CheckBox veteranCheckbox;

    @BindView(R.id.events_owner_multi_rv)
    RecyclerView recyclerView;

    @BindView(R.id.cal_month)
    TextView monthTextView;

    private HorizontalCalendar horizontalCalendar;

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
        filterButton.setTextColor(getResources().getColor( isShow ? R.color.menuItemEnabledTextColor : R.color.menuItemDisabledTextColor));
        ObjectAnimator.ofFloat(filterLayout, "translationY", isShow ? filterLayout.getHeight() : -filterLayout.getHeight()).start();
        filterEnabled = isShow;
    }

    @Override
    public void init() {
        initGrid();
        initSearchView();
        initCheckBoxes();
        initRv();
        initCalendar();
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

    private void initCheckBoxes() {
        maleCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.applyFilters(maleCheckbox.isChecked(), femaleCheckbox.isChecked(), veteranCheckbox.isChecked()));
        femaleCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.applyFilters(maleCheckbox.isChecked(), femaleCheckbox.isChecked(), veteranCheckbox.isChecked()));
        veteranCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.applyFilters(maleCheckbox.isChecked(), femaleCheckbox.isChecked(), veteranCheckbox.isChecked()));
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

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new MarginItemDecoration((int) (getResources().getDimension(R.dimen.rvEventsLeftMargin))));
        final EventRecyclerAdapter adapter = new EventRecyclerAdapter(presenter.getEvents());
        recyclerView.setAdapter(adapter);
    }

    private void initCalendar() {
        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .textSize(14f, 16f, 14f)
                .showTopText(false)
                .selectorColor(getResources().getColor(R.color.calendarAccent))
                .textColor(getResources().getColor(R.color.calendarAccent), getResources().getColor(R.color.calendarAccent))
                .end()
                .addEvents(new CalendarEventsPredicate() {
                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        return makeCalendarEventList(date);
                    }
                })

                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                monthTextView.setText(new SimpleDateFormat("LLLL").format(date.getTime()));
                Toast.makeText(getContext(), DateFormat.format("EEE, MMM d, yyyy", date) + " is selected!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private List<CalendarEvent> makeCalendarEventList(Calendar date) {

        List<CalendarEvent> calendarEventList = new ArrayList<>();
        List<TalkToTailEvent> eventList = presenter.getEvents();

        CalendarEvent calendarEventCare = new CalendarEvent(getResources().getColor(R.color.eventCardCare));
        CalendarEvent calendarEventDog = new CalendarEvent(getResources().getColor(R.color.eventCardDog));
        CalendarEvent calendarEventTreatment =  new CalendarEvent(getResources().getColor(R.color.eventCardTreatment));
        CalendarEvent calendarEventHealth =  new CalendarEvent(getResources().getColor(R.color.eventCardHealth));

        for (TalkToTailEvent e : eventList) {
            Calendar tempDate = e.getEventDate();

            if (tempDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
                    && tempDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)) {

                if (e.getTypeEvent() == AppConstants.CARE_EVENT) {
                    calendarEventList.add(calendarEventCare);
                }
                if (e.getTypeEvent() == AppConstants.DOG_EVENT) {
                    calendarEventList.add(calendarEventDog);
                }
                if (e.getTypeEvent() == AppConstants.TREATMENT_EVENT) {
                    calendarEventList.add(calendarEventTreatment);
                }
                if (e.getTypeEvent() == AppConstants.HEALTH_EVENT) {
                    calendarEventList.add(calendarEventHealth);
                }

            }
        }
        return calendarEventList;
    }

}
