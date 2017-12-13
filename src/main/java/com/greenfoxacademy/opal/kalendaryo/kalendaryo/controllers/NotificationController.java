package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    @PostMapping(value = "/noti")
    public EventResponse eventNotification(@RequestBody EventResponse eventResponse) {
        System.out.println("The kind of the response: " + eventResponse.getKind() + "\n The ID of the notification channel: " + eventResponse.getId() + "\n The ID of the watched event" + eventResponse.getResourceId() + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());
        eventResponseService.saveEventResponse(eventResponse);
        return eventResponse;
    }

    @GetMapping(value = "/allnotis")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }
}
