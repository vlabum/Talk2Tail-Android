package com.talk2tail.common.ui.recyclerevents;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.talk2tail.R;
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;

public class EventViewHolderFactory {

    private static final int UNKNOWN_EVENT = 0;
    private static final int CARE_EVENT = 1;
    private static final int DOG_EVENT = 2;
    private static final int HEATH_EVENT = 3;
    private static final int TREATMENT_EVENT = 4;

    public ViewHolder create(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CARE_EVENT:
                return new ViewHolderCareEvent(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.care_event_item, parent, false));

            case DOG_EVENT:
                return new ViewHolderDogEvent(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.dog_event_item, parent, false));

            case HEATH_EVENT:
                return new ViewHolderHealthEvent(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.health_event_item, parent, false));

            case TREATMENT_EVENT:
                return new ViewHolderTreatmentEvent(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.treatment_event_item, parent, false));

            default:
                return null;
        }

    }

    int getViewType(TalkToTailEvent event) {

        if (event instanceof CareEvent)
            return CARE_EVENT;

        if (event instanceof DogEvent)
            return DOG_EVENT;

        if (event instanceof HealthEvent)
            return HEATH_EVENT;

        if (event instanceof TreatmentEvent)
            return TREATMENT_EVENT;

        return UNKNOWN_EVENT;
    }

}
