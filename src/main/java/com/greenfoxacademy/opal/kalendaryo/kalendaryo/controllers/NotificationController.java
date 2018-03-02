package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    @Autowired
    AuthAndUserService authAndUserService;

    final static Logger logger = Logger.getLogger("logger");

    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(HttpServletRequest request) {
        EventResponse eventResponse = new EventResponse(request);

        if (!eventResponse.validate()) {
            eventResponseService.saveEventResponse(eventResponse);
            logger.info("Event Response saved.");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            System.out.println("Missing: " + eventResponse.getMissingFields());
            logger.info("Missing: " + eventResponse.getMissingFields());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
