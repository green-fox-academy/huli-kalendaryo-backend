package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.model.api.authresponses.PostAuthResponse;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
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
  public ResponseEntity postAuth(@RequestBody GoogleAuth googleAuth, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {
    try {
      PostAuthResponse postAuthResponse = authAndUserService.createPostAuthResponse(clientToken, googleAuth);
      return ResponseEntity.status(200).body(postAuthResponse);
    } catch (ValidationException val) {
      return ResponseEntity.status(400).body(val.getMessage());
    }
  }

  @GetMapping("/auth")
  public ResponseEntity refreshAccessToken (@RequestHeader("email") String email, @RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {
    try {
      Integer attempt = 1;
      String accessToken = authAndUserService.refreshAccessTokenForAndroid(clientToken, email);
      return ResponseEntity.status(200).body(accessToken);
    } catch (ValidationException val) {
      return ResponseEntity.status(400).body(val.getMessage());
    }
  }
}
