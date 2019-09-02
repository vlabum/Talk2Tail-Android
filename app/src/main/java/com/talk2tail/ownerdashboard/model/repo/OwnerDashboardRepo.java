package com.talk2tail.ownerdashboard.model.repo;

import android.net.Uri;

import com.talk2tail.R;
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.ArrayList;
import java.util.Date;
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

        final DogItemDTO dog5 = new DogItemDTO();
        dog5.setDogName("Роман Васильевич");
        dog5.setDogAge(1.1);
        dog5.setWeight(7.9);
        dog5.setGender("M");
        dog5.setPhotoUrl(Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +R.drawable.simpledog).toString());
        dogs.add(dog5);

        return dogs;
    }

    public List<TalkToTailEvent> getTestEvents() {
        List<TalkToTailEvent> events = new ArrayList<>();
        events.add(new CareEvent("Кукусик", "Покормить Кукусика. Посмотреть, будет ли кукситься.", new Date()));
        events.add(new DogEvent("Кудабля", "Найти Кудаблю.", new Date()));
        events.add(new TreatmentEvent("Шарик", "Выкатить шарика из под дивана.", new Date()));
        events.add(new HealthEvent("Травка", "Подстричь травку.", new Date()));
        events.add(new DogEvent("Кудабля", "Проверить, на месте ли Кудабля.", new Date()));
        return events;
    }

}
