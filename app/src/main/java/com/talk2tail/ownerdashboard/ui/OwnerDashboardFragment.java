package com.talk2tail.ownerdashboard.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.ui.BackButtonListener;
import com.talk2tail.main.ui.MainActivity;
import com.talk2tail.ownerdashboard.presenter.OwnerDashboardPresenter;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class OwnerDashboardFragment extends MvpAppCompatFragment implements OwnerDashboardView, BackButtonListener {

    private View view;
    private Unbinder unbinder;

    @InjectPresenter
    OwnerDashboardPresenter presenter;

    @BindView(R.id.owner_dashboard_text)
    protected TextView textView;
//
//    @BindView(R.id.main_container)
//    private FrameLayout frameLayout;

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
        initHorizontalCalendar();
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


    private void initHorizontalCalendar() {
//        FrameLayout frameLayout = findViewById
        /* starts before 1 month from now */
        final Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .selectorColor(Color.parseColor("#84B3FE"))
                .showTopText(false)
                .colorTextMiddle(R.color.colorPrimaryDark, R.color.colorPrimaryDark)
                .colorTextBottom(R.color.colorPrimaryDark, R.color.colorPrimaryDark)
                .sizeMiddleText(16)
                .end()
                .addEvents(new CalendarEventsPredicate() {

                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        List<CalendarEvent> list = new ArrayList<>();
                        CalendarEvent calendarEvent1 = new CalendarEvent(Color.parseColor("#FF0000"));
                        CalendarEvent calendarEvent2 = new CalendarEvent(Color.parseColor("#2196F3"));
                        CalendarEvent calendarEvent3 =  new CalendarEvent(Color.parseColor("#00FF00"));
                        if(date.compareTo(new GregorianCalendar(2019, 7, 22)) == 0) {
                            list.add(calendarEvent1);
                            list.add(calendarEvent2);
                        }
                        if (date.compareTo(new GregorianCalendar(2019, 07, 24)) == 0){
                            list.add(calendarEvent3);
                        }
                        return list;                        // test the date and return a list of CalendarEvent to assosiate with this Date.
                    }
                })
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });
    }

}
