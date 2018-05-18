package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;

@RestController
public class AccountController {

  @Autowired
  AuthAndUserService authAndUserService;

  @GetMapping(value = "/account")
  public ResponseEntity getGoogleAccounts(@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) throws IOException {
    if (!request.getHeader("X-Client-Token").equals("")) {
      return ResponseEntity.status(200).body(authAndUserService.createUserResponseForGetAccounts(clientToken));
    }
    return ResponseEntity.status(401).body("Client token is missing or invalid");
  }

  @Transactional
  @DeleteMapping(value = "/account")
  public ResponseEntity deleteGoogleAccount(@RequestHeader("X-Client-Token") String clientToken, @RequestHeader("email")String email,
                                            HttpServletRequest request) throws IOException {
    if (!request.getHeader("X-Client-Token").equals("")) {
      if (!authAndUserService.checkIfEmailIsLoggedInUser(email, clientToken)) {
        authAndUserService.deleteGoogleAuth(email, clientToken);
        return new ResponseEntity(HttpStatus.OK);
      }
      return ResponseEntity.status(500).body("Can not delete the base login email");
    }
    return ResponseEntity.status(401).body("Client token is missing or invalid");
  }
}
