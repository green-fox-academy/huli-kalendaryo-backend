package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class AccountController {

  @Autowired
  AuthAndUserService authAndUserService;

  @GetMapping(value = "/account")
  public ResponseEntity getGoogleAccounts (@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {

    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping(value = "/account")
  public ResponseEntity deleteGoogleAccount (@RequestHeader("X-Client-Token") String clientToken, @RequestBody GoogleAuth googleAuth) throws IOException {
    KalUser kalUser;
    kalUser = authAndUserService.findUserByClientToken(clientToken);
    authAndUserService.deleteGoogleAuth(googleAuth.getEmail(), kalUser);
    return new ResponseEntity(HttpStatus.OK);
  }
}
