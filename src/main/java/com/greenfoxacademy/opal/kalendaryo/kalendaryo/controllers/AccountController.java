package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Profile("dev")
public class AccountController {

  @GetMapping(value = "/account")
  public ResponseEntity getGoogleAccounts (@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping(value = "/account")
  public ResponseEntity deleteGoogleAccounts (@RequestHeader("X-Client-Token") String clientToken, HttpServletRequest request) {
    return new ResponseEntity(HttpStatus.OK);
  }
}
