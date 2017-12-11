package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.CalendarModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarModelService {

    @Autowired
    CalendarModelRepository calendarModelRepository;

    public CalendarModel setCalendarAttributes(com.google.api.services.calendar.model.Calendar googleCalendar) {
        CalendarModel calendar = new CalendarModel();
        calendar.setCalendarModelId(googleCalendar.getId());
        calendar.setKind(googleCalendar.getKind());
        calendar.setSummary(googleCalendar.getSummary());
        calendar.setName(googleCalendar.getDescription());

        return calendar;
    }

    public void saveCalendar(CalendarModel calendarModel) {
        calendarModelRepository.save(calendarModel);
    }
}
