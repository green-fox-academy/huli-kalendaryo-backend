package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

<<<<<<< HEAD
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendarUpdate;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleCalendarUpdateService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
=======
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
>>>>>>> 6f25c3cabe346ba5c96555ea88fd5ca39f9b585e
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
    GoogleCalendarUpdateService googleCalendarUpdateService;

    @Autowired
    AuthorizeKal authorizeKal;

    @Autowired
    AuthorizeKal authorizeKal;

    @GetMapping("/accesstoken")
    public String getAccessToken(@RequestParam String authCode) throws IOException {
        return authorizeKal.authorize(authCode);
    }

    @GetMapping(value = "/notification")
    public Iterable<GoogleCalendarUpdate> showAllGoogleCalendarUpdate() {
        Iterable<GoogleCalendarUpdate> responses = googleCalendarUpdateService.findAllGoogleCalendarUpdate();
        System.out.println(responses);
        return responses;
    }
}
