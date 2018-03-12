package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarListResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.*;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.CalendarIdService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MergedCalController {

    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    KalendarService kalendarService;

    @Autowired
    CalendarIdService calendarIdService;

    @PostMapping(value = "/calendar")
    public ResponseEntity postMergedCal(@RequestHeader("X-Client-Token") String clientToken,
        @RequestBody MergedCalendarFromAndroid mergedCalendarFromAndroid) throws IOException {
        if (clientToken == null) {
            return ResponseEntity.status(401).body("Client token is missing or invalid");
        }
        Kalendar kalendar = new Kalendar();
        calendarIdService.saveCalendarId(kalendar, mergedCalendarFromAndroid, clientToken);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/calendar")
    public ResponseEntity getMergedCalList(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            MergedCalendarListResponse mergedCalendarListResponse = new MergedCalendarListResponse();
            UserModel user = userModelRepository.findByClientToken(clientToken);
            List<Kalendar> list = kalendarService.findKalendars(user);
            mergedCalendarListResponse.setMergedCalendars(kalendarService.setMergedCalendarResponse(list));
            return new ResponseEntity<>(mergedCalendarListResponse, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Client token is missing or invalid", HttpStatus.UNAUTHORIZED);

    }

}
