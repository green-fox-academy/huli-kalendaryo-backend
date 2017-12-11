package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.getCalendarService;
import com.google.api.services.calendar.Calendar.Events.Watch;
import com.google.api.services.calendar.model.Channel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventChange;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NotificationController {

    EventChange eventChange;

    /*@PostMapping("/noti")
    public EventResponse eventWatch(@RequestBody EventChange eventChange) throws IOException {
        com.google.api.services.calendar.Calendar service = getCalendarService();
        Channel channel = new Channel();
        Watch eventWatch = service.events().watch(eventChange.getId(), channel);
        eventWatch.execute();
        return new EventResponse("api#channel", channel.getId(), eventChange.getId(), "https://www.googleapi.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events/watch" );
    }*/

    @GetMapping(value = "/noti")
    public void eventNotification(@RequestBody EventResponse eventResponse) {

        System.out.println("The kind of the response: " + eventResponse.getKind() + "\n The ID of the notification channel: " + eventResponse.getId() + "\n The ID of the watched event" + eventResponse.getResourceId() + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());
    }

}
