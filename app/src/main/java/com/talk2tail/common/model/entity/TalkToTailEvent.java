package com.talk2tail.common.model.entity;

import com.talk2tail.common.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.model.CalendarEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * Данный класс описывает все возможные типы событий,
 * которые необходимы питомцу
 */
public class TalkToTailEvent extends CalendarEvent {

    @Getter
    @Setter
    protected String eventCaption;

    @Getter
    @Setter
    protected String eventDescription;

    @Getter
    @Setter
    protected int typeEvent;

    @Getter
    protected Calendar eventDate;

    @Getter
    protected String timeStr;

    @Getter
    protected String dateStr;

    //protected int color;

    public TalkToTailEvent(String caption, String description, Calendar date, int typeEvent, int color) {
        super(color);
        this.eventCaption = caption;
        this.eventDescription = description;
        this.description = description;
        switch (typeEvent) {
            case AppConstants.CARE_EVENT:
            case AppConstants.DOG_EVENT:
            case AppConstants.HEALTH_EVENT:
            case AppConstants.TREATMENT_EVENT:
                this.typeEvent = typeEvent;
                break;
            default:
                this.typeEvent = AppConstants.UNKNOWN_EVENT;
        }
        setEventDate(date);
    }

    private void setEventDate(Calendar eventDate) {
        this.eventDate = eventDate;
        setDateTimeStrings();
    }

    private void setDateTimeStrings() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        timeStr = sdfTime.format(eventDate.getTime());
        // TODO: пересмотреть форматирование даты для России и др. стран. Нужны только число и месяц.
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM");
        dateStr = sdfDate.format(eventDate.getTime());
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String getDescription() {
        return eventDescription;
    }

    @Override
    public void setDescription(String description) {
        this.eventDescription = description;
    }
}
