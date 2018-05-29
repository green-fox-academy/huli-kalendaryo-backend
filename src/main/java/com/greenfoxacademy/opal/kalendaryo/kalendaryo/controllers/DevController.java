package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendarUpdate;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleCalendarUpdateService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
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

    @GetMapping("/accesstoken")
    public String getAccessToken(@RequestParam String authCode) throws IOException {
        return authorizeKal.authorize(authCode).getAccessToken();
    }

    @GetMapping(value = "/notification")
    public Iterable<GoogleCalendarUpdate> showAllGoogleCalendarUpdate() {
        Iterable<GoogleCalendarUpdate> responses = googleCalendarUpdateService.findAllGoogleCalendarUpdate();
        System.out.println(responses);
        return responses;
    }
}
