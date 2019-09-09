package com.talk2tail.dogdashboard.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.common.ui.recyclerevents.EventRecyclerAdapter;
import com.talk2tail.common.ui.recyclerevents.MarginItemDecoration;
import com.talk2tail.dogdashboard.presenter.DogDashboardPresenter;
import com.talk2tail.dogdashboard.view.DogDashboardView;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.common.model.DogWeighData;
import com.talk2tail.ownerdashboard.ui.DogItemBigView;
import org.jetbrains.annotations.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DogDashboardFragment extends MvpAppCompatFragment implements DogDashboardView, BackButtonListener {

    private View view;
    private Unbinder unbinder;
    private HorizontalCalendar horizontalCalendar;

    @InjectPresenter
    DogDashboardPresenter presenter;

    @BindView(R.id.dog_dash_grid_layout)
    GridLayout dogGridLayout;
    @BindView(R.id.month)
    TextView monthTextView;
    @BindView(R.id.current_weight)
    TextView lastWeight;
    @BindView(R.id.last_weigh)
    TextView lastWeigh;
    @BindView(R.id.graph)
    GraphView graph;

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


        /* start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        monthTextView.setText(new SimpleDateFormat("LLLL").format(Calendar.getInstance().getTime()));

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


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.events_dog_dash_rv);
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


    @Override
    public void clearDogs() {

    }

    @Override
    public void showAllDogs(boolean isShow) {

    }


    @Override
    public void addDogWeighData(DogWeighData dogWeighData) {
        LineGraphSeries series = dogWeighData.getPoints();
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getViewport().setScrollable(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.addSeries(series);
        setWeightFields(dogWeighData);
    }

    private void setWeightFields(DogWeighData data) {
        if (data.getLastWeight() > 0) {
            lastWeight.setText("" + data.getLastWeight());
            lastWeigh.setText(getResources().getString(R.string.last_wegh) +
                    " " + data.getDaysAgo() + " " +
                    getResources().getString(R.string.days_ago));
        } else {
            lastWeight.setText("-");
            lastWeigh.setText(getResources().getString(R.string.did_not_weigh_record));
        }

    }

    private List<CalendarEvent> makeCalendarEventList(Calendar date) {

        List<CalendarEvent> calendarEventList = new ArrayList<>();
        List<TalkToTailEvent> eventList = presenter.getEvents();

        CalendarEvent calendarEventCare = new CalendarEvent(getResources().getColor(R.color.eventCardCare));
        CalendarEvent calendarEventDog = new CalendarEvent(getResources().getColor(R.color.eventCardDog));
        CalendarEvent calendarEventTreatment =  new CalendarEvent(getResources().getColor(R.color.eventCardTreatment));
        CalendarEvent calendarEventHealth =  new CalendarEvent(getResources().getColor(R.color.eventCardHealth));

        for (TalkToTailEvent e: eventList) {
            Date tempDate = e.getEventDate();

            if (date.getTime().getDay() == tempDate.getDay()){

                if (e instanceof CareEvent){
                    calendarEventList.add(calendarEventCare);
                }
                if (e instanceof DogEvent){
                    calendarEventList.add(calendarEventDog);
                }
                if (e instanceof TreatmentEvent){
                    calendarEventList.add(calendarEventTreatment);
                }
                if (e instanceof HealthEvent){
                    calendarEventList.add(calendarEventHealth);
                }

            }
        }
        return calendarEventList;
    }
}
