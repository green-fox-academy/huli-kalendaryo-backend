package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.google.api.services.calendar.model.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.getCalendarService;

@Controller("/kalendaryoo")
public class CalendarController {

    @GetMapping("calendar/show")
    public String showCalendar() throws IOException {
        com.google.api.services.calendar.Calendar service =
                getCalendarService();

        com.google.api.services.calendar.model.Calendar calendar =
                service.calendars().get("primary").execute();
        System.out.println(calendar.getSummary());
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @PutMapping("calendar/update")
    public String updateCalendar() throws IOException {
        com.google.api.services.calendar.Calendar service =
                getCalendarService();
        com.google.api.services.calendar.model.Calendar calendar =
                service.calendars().get("primary").execute();
        calendar.setSummary("calendarSummary");
        com.google.api.services.calendar.model.Calendar updatedCalendar =
                service.calendars().update(calendar.getId(), calendar).execute();
        System.out.println(updatedCalendar.getEtag());
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @GetMapping("calendar/insert")
    public String insertCalendar() throws IOException {
        com.google.api.services.calendar.Calendar service =
                getCalendarService();

        Calendar calendar = service.calendars().insert(new Calendar()
                .setSummary("helloworld")
        .setTimeZone("America/Los_Angeles"))
        .execute();
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }

    @DeleteMapping("calendar/delete")
    public String deleteCalendar() throws IOException {
        com.google.api.services.calendar.Calendar service =
                getCalendarService();
        service.calendars().delete("secondaryCalendarId").execute();
        return "redirect:https://calendar.google.com/calendar/b/1/r";
    }
}
