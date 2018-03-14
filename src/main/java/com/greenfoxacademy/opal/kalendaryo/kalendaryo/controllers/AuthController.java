package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.GetAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.PostAuthResponse;
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

    @GetMapping("/auth")
    public ResponseEntity getAuth(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            KalUser kalUser = authAndUserService.findUserByClientToken(clientToken);
            UserResponse userResponse = new UserResponse();

            for (int i = 0; i < kalUser.getGoogleAuthList().size() ; i++) {
                GoogleAuth currentGoogleAuth = kalUser.getGoogleAuthList().get(i);
                GetAuthResponse tempGetAuthResponse = new GetAuthResponse(currentGoogleAuth.getEmail(), currentGoogleAuth.getAccessToken());
                userResponse.getGoogleAuths().add(tempGetAuthResponse);
            }

            return ResponseEntity.status(200).body(new UserResponse(kalUser.getId(), kalUser.getUserEmail(), userResponse.getGoogleAuths()));
        }
        return ResponseEntity.status(401).body("Client token is missing or invalid");
    }

    @PostMapping("/auth")
    public PostAuthResponse postAuth(@RequestBody GoogleAuth googleAuth, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        KalUser kalUser;
        if (!request.getHeader("X-Client-Token").equals("")) {
            kalUser = authAndUserService.findUserByClientToken(clientToken);
        } else if (googleAuthRepository.findByEmail(googleAuth.getEmail()) != null) {
            kalUser = authAndUserService.findUserByAuth(googleAuth);
        } else {
            kalUser = new KalUser(authAndUserService.getRandomClientToken());
            kalUser.setUserEmail(googleAuth.getEmail());
        }
        googleAuth.setUser(kalUser);
        authAndUserService.saveGoogleAuth(googleAuth);
        return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), googleAuth.getEmail(), googleAuth.getAccessToken());
    }
}
