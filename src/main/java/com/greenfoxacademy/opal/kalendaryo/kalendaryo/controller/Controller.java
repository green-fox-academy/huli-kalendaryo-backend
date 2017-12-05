package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

@RestController()
public class Controller {

    @GetMapping("/api/calendar/id")
    public Object findCalendar() {
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("applicationName").build();

        // Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
                service.calendars().get('primary').execute();

        return calendar.getSummary();
    }

    @GetMapping("/api/calendarId/events/eventId")
    public Object findEvents() {
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("applicationName").build();

        // Retrieve an event
        Event event = service.events().get('primary', "eventId").execute();
        return event.getSummary();
    }
}
