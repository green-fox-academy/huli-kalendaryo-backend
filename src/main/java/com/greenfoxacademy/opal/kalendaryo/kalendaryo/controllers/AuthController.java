package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.AuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    AuthAndUserService authAndUserService;

    @PostMapping("/auth")
    public AuthResponse postAuth(@RequestBody AuthModel authModel, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        UserModel userModel;
        if (!request.getHeader("X-Client-Token").equals("")) {
            userModel = authAndUserService.findUserByClientToken(clientToken);
        }
        else {
            userModel = new UserModel(authAndUserService.getRandomClientToken());
            userModel.setUserEmail(authModel.getEmail());
            userModel.setId(userModel.getId());
        }
        authModel.setUser(userModel);
        authAndUserService.saveAuthModel(authModel);

        return new AuthResponse(userModel.getId(), userModel.getClientToken(), authModel.getEmail(), authModel.getAccessToken());
    }

    @GetMapping("/auth")
    public ResponseEntity<?> listAuthentications(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
        if (!request.getHeader("X-Client-Token").equals("")) {
            List<AuthModel> listOfAuthentications = (List<AuthModel>) authAndUserService.findUserByClientToken(clientToken);
            return ResponseEntity.ok().body(listOfAuthentications);
        }
        return ResponseEntity.badRequest().body("Client token is missing or invalid");
    }

}
