package com.talk2tail.ownerdashboard.model.repo;

import android.net.Uri;

import com.talk2tail.App;
import com.talk2tail.R;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OwnerDashboardRepo implements IOwnerDashboardRepo {

    @Override
    public List<DogItemDTO> getGoodDoggies(int count) {
        return getTestData().subList(0, count);
    }

    private List<DogItemDTO> getTestData() {
        final List<DogItemDTO> dogs = new ArrayList<>();

        final DogItemDTO dog1 = new DogItemDTO();
        dog1.setDogName("Кукусик");
        dog1.setDogAge(2.5);
        dog1.setWeight(10.3);
        dog1.setGender("M");
        dog1.setPhotoUrl(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.simpledog).toString());
        dogs.add(dog1);

        final DogItemDTO dog2 = new DogItemDTO();
        dog2.setDogName("Кудабля");
        dog2.setDogAge(2.9);
        dog2.setWeight(15.5);
        dog2.setGender("M");
        dog2.setPhotoUrl(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.simpledog).toString());
        dogs.add(dog2);

        final DogItemDTO dog3 = new DogItemDTO();
        dog3.setDogName("Шарик");
        dog3.setDogAge(7.3);
        dog3.setWeight(21.4);
        dog3.setGender("M");
        dog3.setPhotoUrl(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.simpledog).toString());
        dogs.add(dog3);

        final DogItemDTO dog4 = new DogItemDTO();
        dog4.setDogName("Травка");
        dog4.setDogAge(5.3);
        dog4.setWeight(12.4);
        dog4.setGender("F");
        dog4.setPhotoUrl(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.simpledog).toString());
        dogs.add(dog4);

        final DogItemDTO dog5 = new DogItemDTO();
        dog5.setDogName("Тузик с грелкой");
        dog5.setDogAge(10.1);
        dog5.setWeight(7.9);
        dog5.setGender("M");
        dog5.setPhotoUrl(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.simpledog).toString());
        dogs.add(dog5);

        return dogs;
    }

    public List<TalkToTailEvent> getTestEvents() {
        List<TalkToTailEvent> events = new ArrayList<>();

        events.add(new TalkToTailEvent("Кукусик",
                "Покормить Кукусика. Посмотреть, будет ли кукситься.",
                Calendar.getInstance(),
                AppConstants.CARE_EVENT,
                App.getInstance().getResources().getColor(R.color.eventCardCare)));

        events.add(new TalkToTailEvent("Кудабля",
                "Найти Кудаблю.",
                Calendar.getInstance(),
                AppConstants.DOG_EVENT,
                App.getInstance().getResources().getColor(R.color.eventCardDog)));

        events.add(new TalkToTailEvent("Шарик",
                "Выкатить шарика из под дивана.",
                Calendar.getInstance(),
                AppConstants.TREATMENT_EVENT,
                App.getInstance().getResources().getColor(R.color.eventCardTreatment)));

        Calendar cal = Calendar.getInstance();
        cal.set(2019, 8, 10);

        events.add(new TalkToTailEvent("Травка",
                "Подстричь травку.",
                cal,
                AppConstants.HEALTH_EVENT,
                App.getInstance().getResources().getColor(R.color.eventCardHealth)));

        events.add(new TalkToTailEvent("Кудабля",
                "Проверить, на месте ли Кудабля.",
                cal,
                AppConstants.DOG_EVENT,
                App.getInstance().getResources().getColor(R.color.eventCardDog)));
        return events;
    }

}
