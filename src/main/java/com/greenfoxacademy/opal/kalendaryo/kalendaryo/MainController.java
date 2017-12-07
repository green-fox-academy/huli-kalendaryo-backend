package com.greenfoxacademy.opal.kalendaryo.kalendaryo;


import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  private AuthorizationCodeTokenRequest authorizationCodeTokenRequest;
  @PostMapping("/api/{calendarId}/events/")
  public Object insertEvent(@PathVariable(name = "calendarId") String calendarId) {
   authorizationCodeTokenRequest = new AuthorizationCodeTokenRequest();
    return new EventModel();


  }

}
