package com.talk2tail.common.ui.recyclerevents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talk2tail.R;
import com.talk2tail.common.model.event.CareEvent;
import com.talk2tail.common.model.event.DogEvent;
import com.talk2tail.common.model.event.HealthEvent;
import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.common.model.event.TreatmentEvent;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private static final int UNCNOWN_EVENT = 0;
    private static final int CARE_EVENT = 1;
    private static final int DOG_EVENT = 2;
    private static final int HEATH_EVENT = 3;
    private static final int TREATMENT_EVEN = 4;

    private List<TalkToTailEvent> events;

    public EventRecyclerAdapter(List<TalkToTailEvent> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

            default: //TREATMENT_EVEN
                return new ViewHolderTreatmentEvent(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.treatment_event_item, parent, false));

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (events.get(position) instanceof CareEvent)
            return CARE_EVENT;

        if (events.get(position) instanceof DogEvent)
            return DOG_EVENT;

        if (events.get(position) instanceof HealthEvent)
            return HEATH_EVENT;

        if (events.get(position) instanceof TreatmentEvent)
            return TREATMENT_EVEN;

        return UNCNOWN_EVENT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bind(TalkToTailEvent careEvent) {
        }
    }

    public class ViewHolderCareEvent extends ViewHolder {

        TextView caption;
        TextView description;
        TextView time;
        TextView date;

        ViewHolderCareEvent(View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.care_event_caption_tv);
            description = itemView.findViewById(R.id.care_event_description_tv);
            time = itemView.findViewById(R.id.care_event_time_tv);
            date = itemView.findViewById(R.id.care_event_date_tv);
        }

        @Override
        public void bind(TalkToTailEvent careEvent) {
            caption.setText(careEvent.getEventCaption());
            description.setText(careEvent.getEventDescription());
            time.setText(careEvent.getTimeStr());
            date.setText(careEvent.getDateStr());
        }
    }

    public class ViewHolderDogEvent extends ViewHolder {

        TextView caption;
        TextView description;
        TextView time;
        TextView date;

        ViewHolderDogEvent(View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.dog_event_caption_tv);
            description = itemView.findViewById(R.id.dog_event_description_tv);
            time = itemView.findViewById(R.id.dog_event_time_tv);
            date = itemView.findViewById(R.id.dog_event_date_tv);
        }

        @Override
        public void bind(TalkToTailEvent dogEvent) {
            caption.setText(dogEvent.getEventCaption());
            description.setText(dogEvent.getEventDescription());
            time.setText(dogEvent.getTimeStr());
            date.setText(dogEvent.getDateStr());
        }
    }

    public class ViewHolderHealthEvent extends ViewHolder {

        TextView caption;
        TextView description;
        TextView time;
        TextView date;

        ViewHolderHealthEvent(View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.health_event_caption_tv);
            description = itemView.findViewById(R.id.health_event_description_tv);
            time = itemView.findViewById(R.id.health_event_time_tv);
            date = itemView.findViewById(R.id.health_event_date_tv);
        }

        @Override
        public void bind(TalkToTailEvent healthEvent) {
            caption.setText(healthEvent.getEventCaption());
            description.setText(healthEvent.getEventDescription());
            time.setText(healthEvent.getTimeStr());
            date.setText(healthEvent.getDateStr());
        }
    }

    public class ViewHolderTreatmentEvent extends ViewHolder {

        TextView caption;
        TextView description;
        TextView time;
        TextView date;

        ViewHolderTreatmentEvent(View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.treatment_event_caption_tv);
            description = itemView.findViewById(R.id.treatment_event_description_tv);
            time = itemView.findViewById(R.id.treatment_event_time_tv);
            date = itemView.findViewById(R.id.treatment_event_date_tv);
        }

        @Override
        public void bind(TalkToTailEvent treatmentEvent) {
            caption.setText(treatmentEvent.getEventCaption());
            description.setText(treatmentEvent.getEventDescription());
            time.setText(treatmentEvent.getTimeStr());
            date.setText(treatmentEvent.getDateStr());
        }
    }

}
