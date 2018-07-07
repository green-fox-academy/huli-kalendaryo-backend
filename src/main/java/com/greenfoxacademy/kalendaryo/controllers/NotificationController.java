package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.service.GoogleCalendarUpdateService;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendarUpdate;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
public class NotificationController {

    @Autowired
    GoogleCalendarUpdateService googleCalendarUpdateService;

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    KalendarService kalendarService;

    final static Logger logger = Logger.getLogger("logger");

    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(HttpServletRequest request) {
        GoogleCalendarUpdate googleCalendarUpdate = new GoogleCalendarUpdate(request);

        if (!googleCalendarUpdate.validate()) {
            googleCalendarUpdateService.saveGoogleCalendarUpdate(googleCalendarUpdate);
            logger.info("Google Calendar Update saved.");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            System.out.println("Missing: " + googleCalendarUpdate.getMissingFields());
            logger.info("Missing: " + googleCalendarUpdate.getMissingFields());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/notification/{id}")
    public ResponseEntity refreshKalendar(@RequestHeader("X-Client-Token") String clientToken, @PathVariable(name = "id") long kalendarId) {
        try {
            kalendarService.syncCalendar(clientToken, kalendarId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ValidationException val) {
            return new ResponseEntity(val.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
