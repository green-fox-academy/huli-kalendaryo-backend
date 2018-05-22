package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
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
    try {
      return ResponseEntity.status(200).body(authAndUserService.createUserResponseForGetAccounts(clientToken));
    } catch (ValidationException val) {
      return ResponseEntity.status(400).body(val.getMessage());
    }
  }

  @Transactional
  @DeleteMapping(value = "/account")
  public ResponseEntity deleteGoogleAccount(@RequestHeader("X-Client-Token") String clientToken, @RequestHeader("email") String email,
                                            HttpServletRequest request) throws IOException {
    try {
      authAndUserService.deleteGoogleAuth(email, clientToken);
      return new ResponseEntity(HttpStatus.OK);
    } catch (ValidationException val) {
      return ResponseEntity.status(400).body(val.getMessage());
    }
  }
}
