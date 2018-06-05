package com.greenfoxacademy.kalendaryo.authorization.mock;

import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@Service
public class KalendaryoAuthorization implements Authorization {
  @Override
  public String authorize(String authCode) throws IOException {
    return null;
  }

  @Override
  public void deleteCalendar(String accessToken, String calendarId) {

  }

  @Override
  public void createGoogleCalendarUnderAccount(KalendarFromAndroid kalendarFromAndroid, Kalendar kalendar) {

  }
}
