package com.greenfoxacademy.kalendaryo.service.authorization;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.api.GoogleCalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal.NO_MERGED_CALENDAR;
import static org.junit.Assert.*;

public class AuthorizeKalTest {
  
  KalendarFromAndroid kalendarFromAndroid;
  Kalendar kalendar;

  @InjectMocks
  AuthorizeKal authorizeKal;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    kalendarFromAndroid = new KalendarFromAndroid();
    kalendar = new Kalendar();
  }

  @Test
  public void createGoogleCalendarUnderAccount_getOutputGoogleAuthIdNull() throws IOException {
    try {
      authorizeKal.createGoogleCalendarUnderAccount(kalendarFromAndroid,kalendar,1);
    } catch (ValidationException val){
      assertEquals(NO_MERGED_CALENDAR,val.getMessage());
    }
  }

  @Test
  public void createGoogleCalendarUnderAccount_getOutputGoogleAuthIdIsOK() {
    kalendarFromAndroid.setOutputGoogleAuthId("www");
    assertEquals(kalendarFromAndroid.getOutputGoogleAuthId(),"www");
  }

  @Test
  public void getMergedCalendarId_IdIsOK() throws ValidationException {
    String id = "id01";
    kalendarFromAndroid.setOutputGoogleAuthId(id);

    try {
      assertEquals(authorizeKal.getMergedCalendarId(kalendarFromAndroid),"id01");
    } catch (ValidationException val){
      throw new ValidationException(NO_MERGED_CALENDAR);
    }
  }

  @Test
  public void getMergedCalendarId_IdIsNull() {
    String id = null;
    kalendarFromAndroid.setOutputGoogleAuthId(id);

    try {
      authorizeKal.getMergedCalendarId(kalendarFromAndroid);
    } catch (ValidationException val){
      assertEquals(NO_MERGED_CALENDAR,val.getMessage());
    }
  }
}
