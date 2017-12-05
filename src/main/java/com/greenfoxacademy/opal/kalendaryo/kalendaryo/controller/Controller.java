package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

@RestController()
public class Controller {

    private static HttpTransport httpTransport;
    private static JsonFactory jsonFactory;
    private static GoogleCredential credentials;

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
