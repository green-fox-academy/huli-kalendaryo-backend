package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.web.bind.annotation.*;

import com.google.api.services.calendar.Calendar;

@RestController
public class CalendarController {

    private static HttpTransport httpTransport;
    private static JsonFactory jsonFactory;
    private static GoogleCredential credentials;

    @GetMapping("/api/calendar/{calendarId}")
    public void findCalendar(@PathVariable("calendarId") String calenderId) {
        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("applicationName").build();
        // Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
                service.calendars().get('primary').execute();
        System.out.println(calendar.getSummary());
    }


}


