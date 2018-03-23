package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Profile("dev")
public class DevController {

    @Autowired
    EventResponseService eventResponseService;

    @Autowired
    AuthorizeKal authorizeKal;

    @GetMapping("/accesstoken")
    public String getAccessToken(@RequestParam String authCode) throws IOException {
        return authorizeKal.authorize(authCode);
    }

    @GetMapping(value = "/notification")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }
}
