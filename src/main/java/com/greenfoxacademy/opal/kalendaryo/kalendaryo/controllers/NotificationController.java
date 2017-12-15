package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    // at this endpoint we need to receive a google-type object, which can be transferred to our EventModel.
    // EventModel will be saved to the database afterwards.
    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(@RequestBody EventResponse eventResponse) {
        System.out.println("The kind of the response: " + eventResponse.getKind() + "\n The ID of the notification channel: " + eventResponse.getId() + "\n The ID of the watched event" + eventResponse.getResourceId() + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());

        if (eventResponse.getId() == null || eventResponse.getKind() == null || eventResponse.getResourceId() == null || eventResponse.getResourceUri() == null || eventResponse.getEventResponseId() == null) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            eventResponseService.saveEventResponse(eventResponse);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/allnotifications")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }

    @GetMapping("/accesstoken")
    public String getAccessToken() throws IOException {
        return authorize().getAccessToken();
    }
}
