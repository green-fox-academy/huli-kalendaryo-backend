
package com.greenfoxacademy.kalendaryo.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarService {

  private static final JsonFactory JSON_FACTORY =
      JacksonFactory.getDefaultInstance();

  private static HttpTransport HTTP_TRANSPORT;



  @Autowired
  GoogleCalendarRepository googleCalendarRepository;

  @Autowired
  GoogleAuthRepository googleAuthRepository;

  @Autowired
  KalendarRepository kalendarRepository;

  @Autowired
  KalendarService kalendarService;

  @Autowired
  Authorization authorization;

  public void saveGoogleCalendar(GoogleCalendar googleCalendar) {
    googleCalendarRepository.save(googleCalendar);
  }

  public void setGoogleCalendar(Kalendar kalendar, KalendarFromAndroid fromAndroid, String clientToken) throws ValidationException {
    Kalendar newKalendar = kalendarService.setKalendarAttribute(kalendar, fromAndroid,
        clientToken);
    kalendarService.saveKalendar(newKalendar);
    for (int i = 0; i < fromAndroid.getInputGoogleCalendars().length; i++) {
      GoogleCalendar googleCalendar = new GoogleCalendar();
      newKalendar.setLastSync(new Date());
      googleCalendar.setGoogleCalendarId(((fromAndroid.getInputGoogleCalendars()[i].getId())));
      googleCalendar.setVisibility(fromAndroid.getInputGoogleCalendars()[i].getSharingOption());
      googleCalendar.setGoogleAuth(googleAuthRepository.findByEmail(fromAndroid.getInputGoogleCalendars()[i].getEmail()));
      googleCalendar.setId(googleCalendar.getId());
      googleCalendar.setKalendar(kalendar);
      saveGoogleCalendar(googleCalendar);
    }
  }
}
