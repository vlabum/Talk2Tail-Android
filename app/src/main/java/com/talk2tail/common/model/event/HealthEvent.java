package com.talk2tail.common.model.event;

import java.util.Date;

/**
 * Описывает события вакцинации/обработки питомца
 */
public class HealthEvent extends TalkToTailEvent {
    public HealthEvent(String caption, String description, Date date) {
        super();
        this.eventCaption = caption;
        this.eventDescription = description;
        setEventDate(date);
    }
}
