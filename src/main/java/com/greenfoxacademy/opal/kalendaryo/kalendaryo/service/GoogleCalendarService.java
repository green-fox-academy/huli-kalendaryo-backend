package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarService {

    @Autowired
    GoogleCalendarRepository googleCalendarRepository;

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    KalendarService kalendarService;

    public void save(GoogleCalendar googleCalendar) {
        googleCalendarRepository.save(googleCalendar);
    }

    public void saveGoogleCalendar(Kalendar kalendar, KalendarFromAndroid fromAndroid, String clientToken) {
        kalendarService.saveKalendar(kalendar, fromAndroid, clientToken);
        for (int i = 0; i < fromAndroid.getInputCalendarIds().length; i++) {
            GoogleCalendar googleCalendar = new GoogleCalendar();
            googleCalendar.setId(fromAndroid.getInputCalendarIds()[i]);
            googleCalendar.setAuthModel(authModelRepository.findByEmail(fromAndroid.getOutputCalendarId()));
            googleCalendar.setKalendar(kalendar);
            googleCalendarRepository.save(googleCalendar);
        }
    }
}
