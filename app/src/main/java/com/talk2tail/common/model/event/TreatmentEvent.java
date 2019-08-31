package com.talk2tail.common.model.event;

import java.util.Date;

/**
 * Описывает назначения ветеринара
 */
public class TreatmentEvent extends TalkToTailEvent {
    public TreatmentEvent(String caption, String description, Date date) {
        super();
        this.eventCaption = caption;
        this.eventDescription = description;
        setEventDate(date);
    }
}
