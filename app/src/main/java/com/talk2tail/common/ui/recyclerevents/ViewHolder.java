package com.talk2tail.common.ui.recyclerevents;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talk2tail.common.model.event.TalkToTailEvent;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView caption;
    TextView description;
    TextView time;
    TextView date;
    private View itemView;

    ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(TalkToTailEvent careEvent) {
        caption.setText(careEvent.getEventCaption());
        description.setText(careEvent.getEventDescription());
        time.setText(careEvent.getTimeStr());
        date.setText(careEvent.getDateStr());
    }

}
