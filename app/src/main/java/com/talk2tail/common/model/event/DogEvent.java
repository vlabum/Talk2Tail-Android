package com.talk2tail.common.model.event;

import java.util.Date;

/**
 * Описывает различные события, касающиеся питомца
 */
public class DogEvent extends TalkToTailEvent {
    public DogEvent(String caption, String description, Date date) {
        super();
        this.eventCaption = caption;
        this.eventDescription = description;
        setEventDate(date);
    }
}
