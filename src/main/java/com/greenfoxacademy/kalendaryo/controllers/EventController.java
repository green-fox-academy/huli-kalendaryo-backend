package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.model.api.EventListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public class EventController {

  @GetMapping (value = "/{calendarId}/events")
  public EventListResponse getEventList(@RequestHeader("X-Client-Token") String clientToken, @PathVariable(name = "calendarId") String calendarId) {

  }
}
