package com.greenfoxacademy.kalendaryo.service.authorization;

import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface Authorization {

    String authorize(String authCode) throws IOException;

    void deleteCalendar(String accessToken, String calendarId);

    void createGoogleCalendarUnderAccount(KalendarFromAndroid kalendarFromAndroid, Kalendar kalendar);
}
