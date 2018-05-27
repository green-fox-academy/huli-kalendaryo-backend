package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarListResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.*;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleCalendarService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.KalendarService;
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

    @GetMapping(value = "/calendar")
    public ResponseEntity getKalendarList(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            KalendarListResponse kalendarListResponse = kalendarService.makeKalendarListResponse(clientToken);
            return new ResponseEntity<>(kalendarListResponse, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Client token is missing or invalid", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/calendar")
    public ResponseEntity postKalendar(@RequestHeader("X-Client-Token") String clientToken,
        @RequestBody KalendarFromAndroid kalendarFromAndroid) throws IOException {
        if (clientToken == null) {
            return ResponseEntity.status(401).body("Client token is missing or invalid");
        }
        kalendarService.createNewKalendar(clientToken, kalendarFromAndroid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/calendar/{id}")//rethink endpoint name, maybe use kalendar
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
