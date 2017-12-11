package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.services.calendar.model.Event;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.CalendarModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.EventModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventModelService {

    @Autowired
    EventModelRepository eventModelRepository;

    //parameters: google event and google calendar
    public EventModel setAttributes(Event event, com.google.api.services.calendar.model.Calendar googleCalendar) {
        EventModel eventModel = new EventModel();
        eventModel.setEventId(event.getId());
        eventModel.setName(event.getSummary());
        eventModel.setDescription(event.getDescription());

        CalendarModel calendar = new CalendarModel();
        calendar.setCalendarModelId(googleCalendar.getId());
        calendar.setKind(googleCalendar.getKind());
        calendar.setSummary(googleCalendar.getSummary());
        calendar.setName(googleCalendar.getDescription());

        eventModel.setCalendarModel(calendar);

        return eventModel;
    }

    public void saveEvent(EventModel eventModel) {
        eventModelRepository.save(eventModel);
    }


}
