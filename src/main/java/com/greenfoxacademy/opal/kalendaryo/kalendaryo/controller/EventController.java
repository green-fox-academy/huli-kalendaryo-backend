package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controller;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventChange;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {


    @PostMapping(value = "https://www.googleapis.com/calendar/v3/calendars/{calendarId}/events/watch")
    public EventResponse watchEvents (@PathVariable("calendarId") String calendarId,
                               @RequestBody EventChange eventChange) {

        return new EventResponse("api#channel", "channelid", eventChange.getId(), "https://www.googleapis.com/calendar/v3/calendars/" + calendarId + "/events");
    }


}
