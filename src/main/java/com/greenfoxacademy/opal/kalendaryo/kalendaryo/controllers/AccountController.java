package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.UserResponse;
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
import java.util.List;

@RestController
public class AccountController {

  @Autowired
  AuthAndUserService authAndUserService;

  @GetMapping(value = "/account")
  public ResponseEntity getGoogleAccounts(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {
    if (!request.getHeader("X-Client-Token").equals("")) {
      KalUser kalUser = authAndUserService.findUserByClientToken(clientToken);
      List<GoogleAuth> googleAuthList;
      googleAuthList = authAndUserService.listAllGoogleAccounts(kalUser.getId());
      return ResponseEntity.status(200).body(new UserResponse(kalUser.getId(), kalUser.getUserEmail(), googleAuthList));
    }
    return ResponseEntity.status(401).body("Client token is missing or invalid");
  }

  @DeleteMapping(value = "/account")
  public ResponseEntity deleteGoogleAccount(@RequestHeader("X-Client-Token") String clientToken, @RequestBody GoogleAuth googleAuth, HttpServletRequest request) throws IOException {
    if (!request.getHeader("X-Client-Token").equals("")) {
      KalUser kalUser = authAndUserService.findUserByClientToken(clientToken);
      if (!kalUser.getUserEmail().equals(googleAuth.getEmail())) {
        authAndUserService.deleteGoogleAuth(googleAuth.getEmail(), kalUser);
        return new ResponseEntity(HttpStatus.OK);
      }
      return ResponseEntity.status(500).body("Can not delete the base login email");
    }
    return ResponseEntity.status(401).body("Client token is missing or invalid");
  }
}
