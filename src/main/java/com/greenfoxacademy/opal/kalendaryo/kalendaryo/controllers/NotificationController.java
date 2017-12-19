package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public HttpStatus eventNotification(HttpServletRequest request) {

        EventResponse eventResponse = new EventResponse(request);

        if (eventResponse.validate().equals(HttpStatus.OK)) {
            eventResponseService.saveEventResponse(eventResponse);
        } else {
            System.out.println("Missing: " + eventResponse.getMissingFields());
        }

        return eventResponse.validate();
    }

    @GetMapping(value = "/notification")
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
