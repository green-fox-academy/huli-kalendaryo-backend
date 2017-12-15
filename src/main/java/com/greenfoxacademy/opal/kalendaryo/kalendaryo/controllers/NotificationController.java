package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @PostMapping(value = "/notification")
    public ResponseEntity eventNotification(@RequestBody EventResponse eventResponse) {
        System.out.println("The kind of the response: " + eventResponse.getKind()
            + "\n The ID of the notification channel: " + eventResponse.getId()
            + "\n The ID of the watched event" + eventResponse.getResourceId()
            + "\n The resourceUri of the watch: " + eventResponse.getResourceUri());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Length", "0");
        httpHeaders.add("Content-Type", "application/json; utf-8");
        httpHeaders.add("X-Goog-Channel-ID", "3435gtg35452");
        httpHeaders.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        httpHeaders.add("X-Goog-Resource-State", "exists");
        httpHeaders.add("X-Goog-Message-Number", "1");
        httpHeaders.add("X-Goog-Resource-URI",
            "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events");
        //        httpHeaders.add("X-Goog-Channel-Token", "");
        //        httpHeaders.add("X-Goog-Channel-Expiration", "");

        if (eventResponse.getId() == null || eventResponse.getKind() == null
            || eventResponse.getResourceId() == null || eventResponse.getResourceUri() == null
            || eventResponse.getEventResponseId() == null) {
            return new ResponseEntity("not OK", httpHeaders, HttpStatus.NOT_ACCEPTABLE);
        } else {
            eventResponseService.saveEventResponse(eventResponse);
            return new ResponseEntity("OK", httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/allnotifications")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }

    @GetMapping("/accesstoken")
    public String getAccessToken() throws IOException {
        return authorize().getAccessToken();
    }

    @GetMapping("/saveuser")
    public void getRegistration(@RequestParam String email, @RequestParam String accessToken) {
        if (!Objects.equals(authModelRepository.findByEmail(email).getEmail(), email)) {
            authModelRepository.save(new AuthModel(email, accessToken, new UserModel()));
        } else {
            authModelRepository.findByEmail(email);
        }
    }
}
