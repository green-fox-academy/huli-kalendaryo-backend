package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarListResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.*;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.MergedCalendarService;
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
    MergedCalendarRepository mergedCalendarRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    MergedCalendarService mergedCalendarService;

    @PostMapping(value = "/calendar")
    public ResponseEntity postMergedCal(@RequestHeader("X-Client-Token") String clientToken,
        @RequestBody MergedCalendarFromAndroid mergedCalendarFromAndroid) throws IOException {
        if (clientToken == null) {
            return ResponseEntity.status(401).body("Client token is missing or invalid");
        }
        MergedCalendar mergedCalendar = new MergedCalendar();
        mergedCalendar.setOutputCalendarId(mergedCalendarFromAndroid.getOutputCalendarId());
        String[] inputStrings = mergedCalendarFromAndroid.getInputCalendarIds();
        mergedCalendar.setCalendarIds(mergedCalendar.getCalendarsIds(inputStrings));
        mergedCalendar
                .setUser(userModelRepository.findByClientToken(clientToken));
        mergedCalendar
                .setOutputAccount(userModelRepository.findByClientToken(clientToken).getUserEmail());
        mergedCalendarRepository.save(mergedCalendar);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/calendar")
    public ResponseEntity getMergedCalList(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals(null)) {
            MergedCalendarListResponse mergedCalendarListResponse = new MergedCalendarListResponse();
            UserModel user = userModelRepository.findByClientToken(clientToken);
            mergedCalendarService.findMergedCalendars(user);
            mergedCalendarListResponse.setMergedCalendarResponse(user.getMergedCalendarList());
            return new ResponseEntity<>(mergedCalendarListResponse, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Client token is missing or invalid", HttpStatus.UNAUTHORIZED);

    }

}
