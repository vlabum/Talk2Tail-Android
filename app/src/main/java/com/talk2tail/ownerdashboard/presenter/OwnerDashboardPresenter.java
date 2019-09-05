package com.talk2tail.ownerdashboard.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.talk2tail.R;
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;
import com.talk2tail.ownerdashboard.view.OwnerDashboardView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import lombok.Getter;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class OwnerDashboardPresenter extends MvpPresenter<OwnerDashboardView> {
    private static final int DEFAULT_COUNT = 3;

    @Inject
    protected Router router;

    private Scheduler mainThreadScheduler;

    final List<DogItemDTO> dogs = new ArrayList<>();

    @Getter
    final List<TalkToTailEvent> events = new ArrayList<>();

    public OwnerDashboardPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        dogs.addAll(getTestData());
        generateTestEvents();
        getViewState().addDogs(dogs.subList(0, DEFAULT_COUNT));
    }

    private List<DogItemDTO> getTestData() {
        final List<DogItemDTO> dogs = new ArrayList<>();

        final DogItemDTO dog1 = new DogItemDTO();
        dog1.setDogName("Кукусик");
        dog1.setDogAge(2.5);
        dog1.setWeight(10.3);
        dog1.setGender("M");
        dog1.setPhotoUrl(Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +R.drawable.simpledog).toString());
        dogs.add(dog1);

        final DogItemDTO dog2 = new DogItemDTO();
        dog2.setDogName("Кудабля");
        dog2.setDogAge(2.9);
        dog2.setWeight(15.5);
        dog2.setGender("M");
        dog2.setPhotoUrl(Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +R.drawable.simpledog).toString());
        dogs.add(dog2);

        final DogItemDTO dog3 = new DogItemDTO();
        dog3.setDogName("Шарик");
        dog3.setDogAge(7.3);
        dog3.setWeight(21.4);
        dog3.setGender("M");
        dog3.setPhotoUrl(Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +R.drawable.simpledog).toString());
        dogs.add(dog3);

        final DogItemDTO dog4 = new DogItemDTO();
        dog4.setDogName("Травка");
        dog4.setDogAge(5.3);
        dog4.setWeight(12.4);
        dog4.setGender("F");
        dog4.setPhotoUrl(Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +R.drawable.simpledog).toString());
        dogs.add(dog4);

        return dogs;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void backClick() {
        router.exit();
    }

    private void generateTestEvents() {
        events.add(new CareEvent("Кукусик", "Покормить Кукусика. Посмотреть, будет ли кукситься.", new Date()));
        events.add(new DogEvent("Кудабля", "Найти Кудаблю.", new Date()));
        events.add(new TreatmentEvent("Шарик", "Выкатить шарика из под дивана.", new Date()));
        events.add(new HealthEvent("Травка", "Подстричь травку.", new Date(119, 8, 4)));
        events.add(new DogEvent("Кудабля", "Проверить, на месте ли Кудабля.", new Date(119, 8, 4)));
    }
}
