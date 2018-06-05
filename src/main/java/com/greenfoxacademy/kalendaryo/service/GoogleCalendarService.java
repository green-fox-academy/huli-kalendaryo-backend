
package com.greenfoxacademy.kalendaryo.service;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
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

    public void setGoogleCalendar(Kalendar kalendar, KalendarFromAndroid fromAndroid, String clientToken) throws ValidationException {
        Kalendar newKalendar = kalendarService.setKalendarAttribute(kalendar, fromAndroid,
                clientToken);
        kalendarService.saveKalendar(newKalendar);
        for (int i = 0; i < fromAndroid.getInputGoogleCalendars().length; i++) {
            GoogleCalendar googleCalendar = new GoogleCalendar();
            googleCalendar.setId(Long.parseLong(fromAndroid.getInputGoogleCalendars()[i].getId()));
            googleCalendar.setGoogleAuth(googleAuthRepository.findByEmail(fromAndroid.getOutputGoogleAuthId()));
            googleCalendar.setKalendar(kalendar);
            saveGoogleCalendar(googleCalendar);
        }
    }
}
