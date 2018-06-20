package com.greenfoxacademy.kalendaryo.service;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static com.greenfoxacademy.kalendaryo.service.KalendarService.NO_KALENDAR_FOR_KALENDAR_ID;
import static com.greenfoxacademy.kalendaryo.service.KalendarService.USER_NOT_FOUND_TOKEN;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class KalendarServiceTest {

  private MockMvc mock;

  @Mock
  KalUserRepository kalUserRepository;

  @Mock
  Authorization authorization;

  @Mock
  GoogleCalendarRepository googleCalendarRepository;

  @Mock
  KalendarRepository kalendarRepository;

  @Mock
  AuthorizeKal authorizeKal;

  @Mock
  GoogleAuthRepository googleAuthRepository;

  @InjectMocks
  KalendarService kalendarService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void setKalendarAttribute_customNameProvided() throws ValidationException {
    String customName = "Hello";
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertEquals(customName, actualKalendar.getName());
  }

  @Test
  public void setKalendarAttribute_customNameMissing() throws ValidationException {
    String customName = "";
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertTrue(customName, actualKalendar.getName().length() > 0);
  }

  @Test
  public void setKalendarAttribute_customNameIsNull() throws ValidationException {
    String customName = null;
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertTrue(customName, actualKalendar.getName().length() > 0);
  }

  @Test
  public void deleteGoogleCalendar_wrongClientToken() {
    doThrow(new NullPointerException()).when(kalUserRepository).findByClientToken(anyString());

    try {
      kalendarService.deleteKalendar("",2);
    } catch (ValidationException expected) {
      assertEquals(USER_NOT_FOUND_TOKEN, expected.getMessage());
    }
  }

  @Test
  public void deleteGoogleCalendar_notExistingKalendar() {
    KalUser kalUser = new KalUser();

    doThrow(new NullPointerException()).when(kalendarRepository).findKalendarById(2);
    when(kalUserRepository.findByClientToken(anyString())).thenReturn(kalUser);

    try {
      kalendarService.deleteKalendar("",2);
    } catch (ValidationException expected) {
      assertEquals(NO_KALENDAR_FOR_KALENDAR_ID + 2, expected.getMessage());
    }
  }

  @Test
  public void deleteGoogleCalendar_everythingIsOk() {
    Kalendar kalendar = new Kalendar();
    KalUser kalUser = new KalUser();
    kalUser.setUserEmail("");
    kalendar.setUser(kalUser);
    GoogleAuth googleAuth = new GoogleAuth();
    googleAuth.setAccessToken("fjhejecv43n3s");

    when(kalendarRepository.findKalendarById(anyLong())).thenReturn(kalendar);
    when(kalUserRepository.findByClientToken(anyString())).thenReturn(kalUser);
    when(googleAuthRepository.findByUser_IdAndEmail(anyLong(), anyString())).thenReturn(googleAuth);

    try {
      kalendarService.deleteKalendar("",1l);
    } catch (ValidationException ex) {
      ex.printStackTrace();
      fail(ex.getMessage());
    }
  }

  private KalendarFromAndroid generateModelFromName(String name) {
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    kalendarFromAndroid.setCustomName(name);
    return kalendarFromAndroid;
  }
}