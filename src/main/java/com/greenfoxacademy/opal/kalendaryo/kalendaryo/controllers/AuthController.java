package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.AuthGetResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.AuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.UserResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    AuthModelRepository authModelRepository;

    @GetMapping("/auth")
    public ResponseEntity getAuth(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            UserModel userModel = authAndUserService.findUserByClientToken(clientToken);
            UserResponse userResponse = new UserResponse();

            for (int i = 0; i < userModel.getAuthModelList().size() ; i++) {
                AuthModel currentAuthModel = userModel.getAuthModelList().get(i);
                AuthGetResponse currentAuthGetResponse = new AuthGetResponse(currentAuthModel.getEmail(), currentAuthModel.getAccessToken());
                userResponse.getAuthModels().add(currentAuthGetResponse);
            }

            return ResponseEntity.status(200).body(new UserResponse(userModel.getId(), userModel.getUserEmail(), userResponse.getAuthModels()));
        }
        return ResponseEntity.status(401).body("Client token is missing or invalid");
    }

    @PostMapping("/auth")
    public AuthResponse postAuth(@RequestBody AuthModel authModel, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        UserModel userModel;
        if (!request.getHeader("X-Client-Token").equals("")) {
            userModel = authAndUserService.findUserByClientToken(clientToken);
        } else if (authModelRepository.findByEmail(authModel.getEmail()) != null) {
            authModel = authModelRepository.findByEmail(authModel.getEmail());
            userModel= authAndUserService.findUserByAuth(authModel);
        } else {
            userModel = new UserModel(authAndUserService.getRandomClientToken());
            userModel.setUserEmail(authModel.getEmail());
        }
        authModel.setUser(userModel);
        authAndUserService.saveAuthModel(authModel);
        return new AuthResponse(userModel.getId(), userModel.getClientToken(), authModel.getEmail(), authModel.getAccessToken());
    }
}
