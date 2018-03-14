package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarService {

    @Autowired
    GoogleCalendarRepository googleCalendarRepository;

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    KalendarService kalendarService;

    public void saveGoogleCalendar(GoogleCalendar googleCalendar) {
        googleCalendarRepository.save(googleCalendar);
    }

    public void setGoogleCalendar(Kalendar kalendar, KalendarFromAndroid fromAndroid, String clientToken) {
        kalendarService.setKalendar(kalendar, fromAndroid, clientToken);
        for (int i = 0; i < fromAndroid.getInputCalendarIds().length; i++) {
            GoogleCalendar googleCalendar = new GoogleCalendar();
            googleCalendar.setId(fromAndroid.getInputCalendarIds()[i]);
            googleCalendar.setGoogleAuth(googleAuthRepository.findByEmail(fromAndroid.getOutputCalendarId()));
            googleCalendar.setKalendar(kalendar);
            saveGoogleCalendar(googleCalendar);
        }
    }
}
