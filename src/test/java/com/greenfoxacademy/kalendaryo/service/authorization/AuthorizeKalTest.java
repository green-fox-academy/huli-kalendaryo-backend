package com.greenfoxacademy.kalendaryo.service.authorization;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
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

  private MockMvc mock;

  @InjectMocks
  AuthorizeKal authorizeKal;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createGoogleCalendarUnderAccount_getOutputGoogleAuthIdNull() throws IOException {
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    Kalendar kalendar = new Kalendar();

    try {
      authorizeKal.createGoogleCalendarUnderAccount(kalendarFromAndroid,kalendar,1);
    } catch (ValidationException val){
      assertEquals(NO_MERGED_CALENDAR,val.getMessage());
    }
  }

  @Test
  public void createGoogleCalendarUnderAccount_getOutputGoogleAuthIdIsOK() {
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    kalendarFromAndroid.setOutputGoogleAuthId("www");
    assertEquals(kalendarFromAndroid.getOutputGoogleAuthId(),"www");
  }

  @Test
  public void getMergedCalendarId_IdIsOK() throws ValidationException {
    String id = "id01";
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
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
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    kalendarFromAndroid.setOutputGoogleAuthId(id);

    try {
      authorizeKal.getMergedCalendarId(kalendarFromAndroid);
    } catch (ValidationException val){
      assertEquals(NO_MERGED_CALENDAR,val.getMessage());
    }
  }

  @Test
  public void
}
