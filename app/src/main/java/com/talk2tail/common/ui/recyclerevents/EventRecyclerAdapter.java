package com.talk2tail.common.ui.recyclerevents;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talk2tail.common.model.event.TalkToTailEvent;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<TalkToTailEvent> events;

    public EventRecyclerAdapter(List<TalkToTailEvent> events) {
        this.events = events;
    }

    private EventViewHolderFactory eventViewHolderFactory = new EventViewHolderFactory();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return eventViewHolderFactory.create(parent, viewType);
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
        return eventViewHolderFactory.getViewType(events.get(position));
    }

}
