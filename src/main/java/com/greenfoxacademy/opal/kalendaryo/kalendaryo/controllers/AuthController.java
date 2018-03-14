package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.GetAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.PostAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.UserResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
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
    AuthModelRepository authModelRepository;

    @GetMapping("/auth")
    public ResponseEntity getAuth(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            KalUser kalUser = authAndUserService.findUserByClientToken(clientToken);
            UserResponse userResponse = new UserResponse();

            for (int i = 0; i < kalUser.getAuthModelList().size() ; i++) {
                AuthModel currentAuthModel = kalUser.getAuthModelList().get(i);
                GetAuthResponse tempGetAuthResponse = new GetAuthResponse(currentAuthModel.getEmail(), currentAuthModel.getAccessToken());
                userResponse.getAuthModels().add(tempGetAuthResponse);
            }

            return ResponseEntity.status(200).body(new UserResponse(kalUser.getId(), kalUser.getUserEmail(), userResponse.getAuthModels()));
        }
        return ResponseEntity.status(401).body("Client token is missing or invalid");
    }

    @PostMapping("/auth")
    public PostAuthResponse postAuth(@RequestBody AuthModel authModel, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        KalUser kalUser;
        if (!request.getHeader("X-Client-Token").equals("")) {
            kalUser = authAndUserService.findUserByClientToken(clientToken);
        } else if (authModelRepository.findByEmail(authModel.getEmail()) != null) {
            kalUser = authAndUserService.findUserByAuth(authModel);
        } else {
            kalUser = new KalUser(authAndUserService.getRandomClientToken());
            kalUser.setUserEmail(authModel.getEmail());
        }
        authModel.setUser(kalUser);
        authAndUserService.saveAuthModel(authModel);
        return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), authModel.getEmail(), authModel.getAccessToken());
    }
}
