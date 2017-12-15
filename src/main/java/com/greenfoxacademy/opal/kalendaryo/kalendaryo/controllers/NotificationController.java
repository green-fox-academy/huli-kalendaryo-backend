package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(@RequestBody EventResponse eventResponse) {
        System.out.println("The kind of the response: " + eventResponse.getKind() + "\n The ID of the notification channel: " + eventResponse.getId() + "\n The ID of the watched event" + eventResponse.getResourceId() + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());

        if (eventResponse.getId() == null || eventResponse.getKind() == null || eventResponse.getResourceId() == null || eventResponse.getResourceUri() == null || eventResponse.getEventResponseId() == null) {
            return new ResponseEntity("not OK", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            eventResponseService.saveEventResponse(eventResponse);
            return new ResponseEntity("OK", HttpStatus.OK);
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

    @GetMapping("/saveUser")
    public void getRegistration(@RequestParam Long user_ID, @RequestParam String client_token) {
        if (userModelRepository.findById(user_ID).getId() != user_ID) {
            userModelRepository.save(new UserModel());
        } else {
            userModelRepository.findById(user_ID);
        }
    }
}

// user_ID, client_token
