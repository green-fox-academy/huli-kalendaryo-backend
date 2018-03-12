package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
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

    public void save(CalendarId calendarId) {
        calendarIdRepository.save(calendarId);
    }

    public void saveCalendarId(Kalendar kalendar, MergedCalendarFromAndroid fromAndroid, String clientToken) {
        mergedCalendarService.saveMergedCalendar(kalendar, fromAndroid, clientToken);
        for (int i = 0; i < fromAndroid.getInputCalendarIds().length; i++) {
            CalendarId calendarId = new CalendarId();
            calendarId.setId(fromAndroid.getInputCalendarIds()[i]);
            calendarId.setAuthModel(authModelRepository.findByEmail(fromAndroid.getOutputCalendarId()));
            calendarId.setKalendar(kalendar);
            calendarIdRepository.save(calendarId);
        }
    }
}
