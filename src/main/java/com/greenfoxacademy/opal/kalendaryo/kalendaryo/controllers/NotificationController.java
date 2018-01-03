package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthResponse;
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

    @PostMapping("/postAuth")
    public AuthResponse getRegistration(@RequestBody AuthModel authModel, @RequestHeader("X-Client-Token") String clientToken) throws IOException {
        UserModel userModel;
        if (clientToken != null) {
            userModel = authAndUserService.findUserByClientToken(clientToken);
        }
        else {
            userModel = new UserModel();
            userModel.setUserEmail(authModel.getEmail());
            authAndUserService.saveUserModel(userModel);
        }
        authModel.setUser(userModel);
        authAndUserService.saveAuthModel(authModel);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(userModel.getId());
        authResponse.setClientToken(userModel.getClientToken());
        authResponse.setAuthId();
        authResponse.setAccessToken(authModel.getAccessToken());

        return authResponse;
    }


    @GetMapping(value = "/notification")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }
}
