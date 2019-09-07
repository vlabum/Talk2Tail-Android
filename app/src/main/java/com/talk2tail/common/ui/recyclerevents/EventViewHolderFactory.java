package com.talk2tail.common.ui.recyclerevents;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.talk2tail.R;
import com.talk2tail.common.AppConstants;
import com.talk2tail.common.model.event.TalkToTailEvent;

public class EventViewHolderFactory {


    public ViewHolder create(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.care_event_item, parent, false);
        ViewHolderCareEvent holder = new ViewHolderCareEvent(view);

        ImageView icon = view.findViewById(R.id.care_event_iv);
        CardView mainCard = view.findViewById(R.id.care_event_cv);

        switch (viewType) {
            case AppConstants.CARE_EVENT:
                icon.setImageResource(R.drawable.ic_t2t_care_event);
                mainCard.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(R.color.eventCardCare)));
                break;

            case AppConstants.DOG_EVENT:
                icon.setImageResource(R.drawable.ic_t2t_dog_event);
                mainCard.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(R.color.eventCardDog)));
                break;

            case AppConstants.HEALTH_EVENT:
                icon.setImageResource(R.drawable.ic_t2t_health_event);
                mainCard.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(R.color.eventCardHealth)));
                break;

            case AppConstants.TREATMENT_EVENT:
                icon.setImageResource(R.drawable.ic_t2t_treatment_event);
                mainCard.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(R.color.eventCardTreatment)));
                break;

        }

        return holder;

//        switch (viewType) {
//            case CARE_EVENT:
//                return new ViewHolderCareEvent(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.care_event_item, parent, false));
//
//            case DOG_EVENT:
//                return new ViewHolderDogEvent(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.dog_event_item, parent, false));
//
//            case HEALTH_EVENT:
//                return new ViewHolderHealthEvent(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.health_event_item, parent, false));
//
//            case TREATMENT_EVENT:
//                return new ViewHolderTreatmentEvent(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.treatment_event_item, parent, false));
//
//            default:
//                return null;
//        }

    }

    int getViewType(TalkToTailEvent event) {
        return event.getTypeEvent();
    }

}
