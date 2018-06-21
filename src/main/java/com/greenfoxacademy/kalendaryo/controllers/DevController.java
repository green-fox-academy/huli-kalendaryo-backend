package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.service.GoogleCalendarUpdateService;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendarUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Profile("dev")
public class DevController {

    @Autowired
    GoogleCalendarUpdateService googleCalendarUpdateService;

    @Autowired
    AuthorizeKal authorizeKal;

    @Autowired
    KalendarService kalendarService;

    @PostMapping("/accesstoken")
    public String getAccessToken(@RequestParam String authCode) throws IOException {
        return authorizeKal.authorize(authCode).getAccessToken();
    }

    @GetMapping(value = "/notification/{id}")
    public ResponseEntity refreshKalendar(@RequestHeader("X-Client-Token") String clientToken,
                                                      @PathVariable(name = "id") long kalendarId) {
        try {
            kalendarService.syncCalendar(clientToken, kalendarId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ValidationException val) {
            return new ResponseEntity(val.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
