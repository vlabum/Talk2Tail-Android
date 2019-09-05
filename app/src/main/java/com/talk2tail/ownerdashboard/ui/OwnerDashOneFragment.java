package com.talk2tail.ownerdashboard.ui;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;
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

public class OwnerDashOneFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {

    @InjectPresenter
    OwnerDashboardPresenter presenter;
    private View view;
    private Unbinder unbinder;

    @BindView(R.id.owner_one_grid_layout)
    GridLayout dogGridLayout;
    @BindView(R.id.month)
    TextView monthTextView;

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

    private HorizontalCalendar horizontalCalendar;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_owner_dashboard_one, container, false);
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
