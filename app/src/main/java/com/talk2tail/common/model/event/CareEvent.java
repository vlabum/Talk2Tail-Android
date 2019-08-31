package com.talk2tail.common.model.event;

import java.util.Date;

/**
 * Уход за питомцем
 */
public class CareEvent extends TalkToTailEvent {
    public CareEvent(String caption, String description, Date date) {
        super();
        this.eventCaption = caption;
        this.eventDescription = description;
        setEventDate(date);
    }
}
