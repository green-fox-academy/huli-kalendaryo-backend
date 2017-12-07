package com.greenfoxacademy.opal.kalendaryo.kalendaryo;


import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


  @PostMapping("/api/{calendarId}/events/")
  public Object insertEvent(@PathVariable(name = "calendarId") String calendarId) {
   
    return new EventModel();


  }

}
