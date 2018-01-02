package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@RestController
public class NotificationController {

    @Autowired
    EventResponseService eventResponseService;

    @Autowired
    AuthAndUserService authAndUserService;

    @PostMapping(value = "/notification")
    public HttpStatus eventNotification(HttpServletRequest request) {
        EventResponse eventResponse = new EventResponse(request);

        if (!eventResponse.validate()) {
            eventResponseService.saveEventResponse(eventResponse);
            return HttpStatus.OK;
        } else {
            System.out.println("Missing: " + eventResponse.getMissingFields());
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/accesstoken")
    public String getAccessToken(@RequestParam String authCode) throws IOException {
        return authorize(authCode);
    }

    @PostMapping("/auth")
    public UserModel getRegistration(@RequestBody AuthModel authModel) throws IOException {
        if (!authModel.equals(authAndUserService.findAuthModelByEmail(authModel.getEmail()))) {
            authAndUserService
                    .saveAuthModel(new AuthModel(authModel.getEmail(), authModel.getAuthCode(), authModel.getDisplayName(),new UserModel()));
        }else {
            authAndUserService.saveAuthModel(authModel);
        }
        UserModel savedUser = authAndUserService.getUserModel(authModel);
        return savedUser;
    }

    @GetMapping(value = "/notification")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }
}
