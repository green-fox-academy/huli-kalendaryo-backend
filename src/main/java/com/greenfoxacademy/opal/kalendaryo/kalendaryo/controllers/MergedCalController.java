package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MergedCalController {

  @Autowired
  MergedCalendarRepository mergedCalendarRepository;

  @Autowired
  UserModelRepository userModelRepository;

  @Autowired
  AuthModelRepository authModelRepository;

  @PostMapping(value = "/calendar")
  public ResponseEntity postMergedCal(@RequestHeader String clientToken, @RequestBody MergedCalendarFromAndroid mergedCalendarFromAndroid) {
    MergedCalendar mergedCalendar = new MergedCalendar();
    mergedCalendar.setUserId(userModelRepository.findByClientToken(clientToken).getId());
    mergedCalendar.setOutputCalendarId(mergedCalendarFromAndroid.getOutputCalendarId());
    List<String> inputStrings = mergedCalendarFromAndroid.getInputCalendaIds();

    List<CalendarId> calendarIds = new ArrayList<>();
    for (int i = 0; i < inputStrings.size(); i++) {
      CalendarId calendarId = new CalendarId();
      calendarId.setId(inputStrings.get(i));
      calendarIds.add(calendarId);
    }
    mergedCalendar.setCalendarId(calendarIds);
    mergedCalendar.setUserName(userModelRepository.findByClientToken(clientToken).getUserEmail());
    mergedCalendar.setOutputAccount(userModelRepository.findByClientToken(clientToken).getUserEmail());
    mergedCalendarRepository.save(mergedCalendar);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(value = "/calendar")
  public MergedCalendarResponse getMergedCalList() {
    return new MergedCalendarResponse();
  }

}
