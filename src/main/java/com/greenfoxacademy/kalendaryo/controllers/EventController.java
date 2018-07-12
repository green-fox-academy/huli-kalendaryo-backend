package com.greenfoxacademy.kalendaryo.controllers;

import com.google.api.services.calendar.model.Events;
import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    AuthorizeKal authorizeKal;

    @Autowired
    GoogleCalendarRepository googleCalendarRepository;

    @Autowired
    GoogleAuthRepository googleAuthRepository;
    
    @Autowired
    KalUserRepository kalUserRepository;
    


    @GetMapping(value = "/events")
    public ResponseEntity getEventList(@RequestHeader("X-Client-Token") String clientToken) throws IOException {
        List<GoogleCalendar> googleCalendars = new ArrayList<>();
        List<Events> allEvents = new ArrayList<>();
        List<GoogleAuth> googleAuths = googleAuthRepository.findAllByUser(kalUserRepository.findByClientToken(clientToken));

        for (GoogleAuth googleAuth : googleAuths) {
            googleCalendars = googleAuth.getGoogleCalendars();
        }

        for (GoogleCalendar googleCalendar : googleCalendars) {
            Events events = authorizeKal.getEventList(googleCalendar);
            allEvents.add(events);
        }
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }
}
