package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    private static HttpTransport httpTransport;
    private static JsonFactory jsonFactory;
    private static GoogleCredential credentials;

    @GetMapping("/api/{calendarId}/events/{eventId}")
    public void findEvents(@PathVariable("calendarId") String calendarId, @PathVariable("eventId") String eventId) {
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("applicationName").build();

        // Retrieve an event
        Event event = service.events().get('primary', "eventId").execute();

        System.out.println(event.getSummary());
    }

    @PutMapping("api/calendars/{calendarId}/events/{eventId}")
    public void update(@PathVariable("calendarId") String calendarId, @PathVariable("eventId") String eventId) {
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("applicationName").build();

        // Retrieve the event from the API
        Event event = service.events().get("primary", "eventId").execute();

        // Make a change
        event.setSummary("Appointment at Somewhere");

        // Update the event
        Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
        System.out.println(updatedEvent.getUpdated());
    }
}


