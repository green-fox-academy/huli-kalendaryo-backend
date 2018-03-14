package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendarUpdate;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleCalendarUpdateService;
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

    final static Logger logger = Logger.getLogger("logger");

    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(HttpServletRequest request) {
        GoogleCalendarUpdate googleCalendarUpdate = new GoogleCalendarUpdate(request);

        if (!googleCalendarUpdate.validate()) {
            googleCalendarUpdateService.saveEventResponse(googleCalendarUpdate);
            logger.info("Event Response saved.");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            System.out.println("Missing: " + googleCalendarUpdate.getMissingFields());
            logger.info("Missing: " + googleCalendarUpdate.getMissingFields());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
