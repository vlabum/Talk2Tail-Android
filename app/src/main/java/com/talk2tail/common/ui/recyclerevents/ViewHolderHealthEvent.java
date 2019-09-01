package com.talk2tail.common.ui.recyclerevents;

import android.view.View;

import com.talk2tail.R;

class ViewHolderHealthEvent extends ViewHolder {

    ViewHolderHealthEvent(View itemView) {
        super(itemView);
        caption = itemView.findViewById(R.id.health_event_caption_tv);
        description = itemView.findViewById(R.id.health_event_description_tv);
        time = itemView.findViewById(R.id.health_event_time_tv);
        date = itemView.findViewById(R.id.health_event_date_tv);
    }

}
