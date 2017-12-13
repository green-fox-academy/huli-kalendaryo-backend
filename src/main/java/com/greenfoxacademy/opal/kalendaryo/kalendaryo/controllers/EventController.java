package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.getCalendarService;

@Controller
@RequestMapping("/event")
public class EventController {

    DateTime startDateTime = new DateTime("2017-12-09T09:00:00-07:00");
    EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/Los_Angeles");

    DateTime endDateTime = new DateTime("2017-12-10T17:00:00-07:00");
    EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/Los_Angeles");

    @GetMapping("/insert")
    public String insertEvent() throws IOException {

        //Google Event
        com.google.api.services.calendar.Calendar service = getCalendarService();
        Event event = service.events().insert("primary", new Event()
            .setDescription("New Event")
            .setSummary("Barbi is testing the database")
            .setStart(start)
            .setEnd(end))
            .execute();

        return "redirect:https://calendar.google.com/calendar/b/2/r";
    }

    @GetMapping("/update")
    public String updateEvent() throws IOException {
        com.google.api.services.calendar.Calendar service =
            getCalendarService();
        Event event = service.events().get("primary", "7nhdmehd85ogf46u0sdcpmfq7h").execute();
        event.setSummary("Trolloloooo!!!");

        Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @GetMapping("/list")
    public String listEvents() throws IOException {
        com.google.api.services.calendar.Calendar service = getCalendarService();
        String pageToken = null;
        do {
            Events events = service.events().list("primary").setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                System.out.println(event.getSummary());
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @GetMapping("/find")
    public String getEvent() throws IOException {
        com.google.api.services.calendar.Calendar service = getCalendarService();
        Event event = service.events().get("primary", "7nhdmehd85ogf46u0sdcpmfq7h").execute();
        System.out.println(event.getSummary());
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @DeleteMapping("/delete")
    public String deleteEvent() throws IOException {
        com.google.api.services.calendar.Calendar service = getCalendarService();
        service.events().delete("primary", "eventId").execute();
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }
}
