package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MergedCalController {

  @Autowired
  MergedCalendarRepository mergedCalendarRepository;

  @PostMapping(value = "/calendar")
  public ResponseEntity postMergedCal(@RequestBody MergedCalendar mergedCalendar) {
    mergedCalendarRepository.save(mergedCalendar);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(value = "/calendar")
  public MergedCalendarResponse getMergedCalList() {
    return new MergedCalendarResponse();
  }

}
