package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.kalendaryo.service.GoogleCalendarService;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.api.KalendarListResponse;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KalendarController {

    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalendarService kalendarService;

    @Autowired
    GoogleCalendarService googleCalendarService;

    @Autowired
    AuthorizeKal authorizeKal;

    @GetMapping(value = "/kalendar")
    public ResponseEntity getKalendarList(@RequestHeader("X-Client-Token") String clientToken) {
        try {
            KalendarListResponse kalendarListResponse = kalendarService.getKalendarsByClientToken(clientToken);
            return new ResponseEntity<>(kalendarListResponse, HttpStatus.OK);
        } catch (ValidationException val) {
            return new ResponseEntity(val.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/kalendar")
    public ResponseEntity postKalendar(@RequestHeader("X-Client-Token") String clientToken,
        @RequestBody KalendarFromAndroid kalendarFromAndroid) {
        try {
            kalendarService.createNewKalendar(clientToken, kalendarFromAndroid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ValidationException val) {
            return new ResponseEntity(val.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping(value = "/kalendar/{id}")
    public ResponseEntity deleteKalendar(@RequestHeader("X-Client-Token") String clientToken,
                                         @PathVariable(name = "id") long kalendarId) {
        try {
            kalendarService.deleteKalendar(clientToken, kalendarId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ValidationException val) {
            return new ResponseEntity(val.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
