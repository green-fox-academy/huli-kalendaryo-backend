package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarIdService {

    @Autowired
    CalendarIdRepository calendarIdRepository;

    public void save(CalendarId calendarId) {
        calendarIdRepository.save(calendarId);
    }

    public void getCalendarsIds(MergedCalendarFromAndroid fromAndroid,MergedCalendar mergedCalendar) {

        for (int i = 0; i < fromAndroid.getInputCalendarIds().length; i++) {
            CalendarId calendarId = new CalendarId();
            calendarId.setId(fromAndroid.getInputCalendarIds()[i]);
            calendarId.setMergedCalendar(mergedCalendar);
            calendarIdRepository.save(calendarId);
        }

    }
}
