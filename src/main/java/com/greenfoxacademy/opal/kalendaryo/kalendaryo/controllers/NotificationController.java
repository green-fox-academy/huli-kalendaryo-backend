package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NotificationController {

    @GetMapping(value = "/noti")
    public void eventNotification(@RequestBody EventResponse eventResponse) {
        System.out.println("The kind of the response: " + eventResponse.getKind() + "\n The ID of the notification channel: " + eventResponse.getId() + "\n The ID of the watched event" + eventResponse.getResourceId() + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());
    }
}
