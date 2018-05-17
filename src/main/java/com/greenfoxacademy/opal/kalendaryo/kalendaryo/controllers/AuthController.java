package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.authresponses.GetAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.authresponses.PostAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.UserResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class AuthController {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @PostMapping("/auth")
    public PostAuthResponse postAuth(@RequestBody GoogleAuth googleAuth, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        KalUser kalUser;
        if (!request.getHeader("X-Client-Token").equals("")) {
            kalUser = authAndUserService.findUserByClientToken(clientToken);
        } else if (authAndUserService.findUserByUserMail(googleAuth.getEmail()) != null) {
            kalUser = authAndUserService.findUserByUserMail(googleAuth.getEmail());
        } else {
            kalUser = new KalUser(authAndUserService.getRandomClientToken());
            kalUser.setUserEmail(googleAuth.getEmail());
        }
        googleAuth.setUser(kalUser);
        if (authAndUserService.checkIfGoogleAuthExist(googleAuth, kalUser.getId())) {
            googleAuth = googleAuthRepository.findByEmailAndUser_Id(googleAuth.getEmail(),kalUser.getId());
            return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), googleAuth.getEmail(), googleAuth.getAccessToken());
        }
        authAndUserService.saveGoogleAuth(googleAuth);
        return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), googleAuth.getEmail(), googleAuth.getAccessToken());
    }
}
