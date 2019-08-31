package com.talk2tail.common.model.event;

import android.graphics.drawable.Icon;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Данный класс описывает все возможные типы событий,
 * которые необходимы питомцу
 */
public abstract class TalkToTailEvent {

    @Getter
    @Setter
    protected String eventCaption;

    @Getter
    @Setter
    protected String eventDescription;

    @Getter
    @Setter
    protected Icon eventIcon;

    @Getter
    protected Date eventDate;

    @Getter
    protected String timeStr;

    @Getter
    protected String dateStr;

    void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        setDateTimeStrings();
    }

    private void setDateTimeStrings() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        timeStr = sdfTime.format(eventDate);
        // TODO: пересмотреть форматирование даты для России и др. стран. Нужны только число и месяц.
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM");
        dateStr = sdfDate.format(eventDate);
    }

}
