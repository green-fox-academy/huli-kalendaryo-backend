package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarIdService {

    @Autowired
    CalendarIdRepository calendarIdRepository;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    @Autowired
    MergedCalendarService mergedCalendarService;

    public void saveCalendarId(CalendarId calendarId) {
        calendarIdRepository.save(calendarId);
    }

    public void setCalendarId(MergedCalendar mergedCalendar, MergedCalendarFromAndroid fromAndroid, String clientToken) {
        mergedCalendarService.setMergedCalendar(mergedCalendar, fromAndroid, clientToken);
        for (int i = 0; i < fromAndroid.getInputCalendarIds().length; i++) {
            CalendarId calendarId = new CalendarId();
            calendarId.setId(fromAndroid.getInputCalendarIds()[i]);
            calendarId.setAuthModel(authModelRepository.findByEmail(fromAndroid.getOutputCalendarId()));
            calendarId.setMergedCalendar(mergedCalendar);
            saveCalendarId(calendarId);
        }
    }
}
