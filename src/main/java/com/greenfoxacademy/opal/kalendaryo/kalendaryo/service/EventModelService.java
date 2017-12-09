package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.services.calendar.model.Event;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.Calendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.EventModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventModelService {

    @Autowired
    EventModelRepository eventModelRepository;

    public EventModel setAttributes(Event event, com.google.api.services.calendar.model.Calendar googleCalendar) {
        EventModel eventModel = new EventModel();
        eventModel.setEventId(event.getId());
        eventModel.setName(event.getSummary());
        eventModel.setDescription(event.getDescription());

        Calendar calendar = new Calendar();
        calendar.setCalendarId(googleCalendar.getId());
        calendar.setKind(googleCalendar.getKind());
        calendar.setSummary(googleCalendar.getSummary());
        calendar.setName(googleCalendar.getDescription());

        eventModel.setCalendar(calendar);

        return eventModel;
    }

    public void saveEvent(EventModel eventModel) {
        eventModelRepository.save(eventModel);
    }


}
