package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.Calendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    public Calendar setCalendarAttributes(com.google.api.services.calendar.model.Calendar googleCalendar) {
        Calendar calendar = new Calendar();
        calendar.setCalendarId(googleCalendar.getId());
        calendar.setKind(googleCalendar.getKind());
        calendar.setSummary(googleCalendar.getSummary());
        calendar.setName(googleCalendar.getDescription());

        return calendar;
    }

    public void saveCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }
}
