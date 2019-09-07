package com.talk2tail.common.ui.recyclerevents;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talk2tail.R;
import com.talk2tail.common.model.entity.TalkToTailEvent;

import lombok.Getter;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView caption;
    private TextView description;
    private TextView time;
    private TextView date;

    @Getter
    private View itemView;

    ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        caption = itemView.findViewById(R.id.card_event_caption_tv);
        description = itemView.findViewById(R.id.card_event_description_tv);
        time = itemView.findViewById(R.id.card_event_time_tv);
        date = itemView.findViewById(R.id.card_event_date_tv);
    }

    public void bind(TalkToTailEvent careEvent) {
        caption.setText(careEvent.getEventCaption());
        description.setText(careEvent.getEventDescription());
        time.setText(careEvent.getTimeStr());
        date.setText(careEvent.getDateStr());
    }

}
