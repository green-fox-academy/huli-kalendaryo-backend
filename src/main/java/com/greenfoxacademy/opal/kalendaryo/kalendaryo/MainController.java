package com.greenfoxacademy.opal.kalendaryo.kalendaryo;


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
