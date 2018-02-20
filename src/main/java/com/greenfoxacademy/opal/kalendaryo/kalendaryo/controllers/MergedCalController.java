package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.*;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    @PostMapping(value = "/calendar")
    public ResponseEntity postMergedCal(@RequestHeader("X-Client-Token") String clientToken,
                                        @RequestBody MergedCalendarFromAndroid mergedCalendarFromAndroid) {
        MergedCalendar mergedCalendar = new MergedCalendar();
        mergedCalendar.setOutputCalendarId(mergedCalendarFromAndroid.getOutputCalendarId());
        String[] inputStrings = mergedCalendarFromAndroid.getInputCalendarIds();
        mergedCalendar.setCalendarIds(mergedCalendar.getCalendarIds(inputStrings));
        mergedCalendar
                .setUsername(userModelRepository.findByClientToken(clientToken).getUserEmail());
        mergedCalendar
                .setOutputAccount(userModelRepository.findByClientToken(clientToken).getUserEmail());
        mergedCalendarRepository.save(mergedCalendar);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/calendar")
    public ResponseEntity getMergedCalList(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        UserModel userModel;
        if (!request.getHeader("X-Client-Token").equals("")) {
            MergedCalendarResponse mergedCalendarResponse = new MergedCalendarResponse();
            mergedCalendarResponse.setMergedCalendar(mergedCalendarRepository.findMergedCalendarsByUsername(userModelRepository.findByClientToken(clientToken).getUserEmail()));
            return ResponseEntity.ok(mergedCalendarResponse);
        }
        return ResponseEntity.badRequest().body("Client token is missing or invalid");

    }
}
