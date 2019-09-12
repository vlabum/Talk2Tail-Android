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
import com.talk2tail.common.model.entity.TalkToTailEvent;

public class EventViewHolderFactory {


    public ViewHolder create(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        ImageView icon = view.findViewById(R.id.card_event_iv);
        CardView mainCard = view.findViewById(R.id.card_event_cv);

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

    }

    int getViewType(TalkToTailEvent event) {
        return event.getTypeEvent();
    }

}
