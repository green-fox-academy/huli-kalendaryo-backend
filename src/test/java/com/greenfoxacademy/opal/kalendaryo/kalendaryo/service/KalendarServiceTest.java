package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
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
  public void setKalendarAttribute_customNameProvided() {
    String customName = "Hello";
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertEquals(customName, actualKalendar.getName());
  }

  @Test
  public void setKalendarAttribute_customNameMissing() {
    String customName = "";
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertTrue(customName, actualKalendar.getName().length() > 0);
  }

  @Test
  public void setKalendarAttribute_customNameIsNull() {
    String customName = null;
    KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

    Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

    assertTrue(customName, actualKalendar.getName().length() > 0);
  }

  @Test
  public void deleteGoogleCalendar_wrongClientToken() {
    String expectedMessage = "No such user in the database";
    doThrow(new NullPointerException()).when(kalUserRepository).findByClientToken(anyString());

    try {
      kalendarService.deleteKalendar("",1l);
    } catch (ValidationException expected) {
      assertEquals(expectedMessage, expected.getMessage());
    }
  }

  @Test
  public void deleteGoogleCalendar_notExistingKalendar() {
    String expectedMessage = "No such kalendar in the database";
    doThrow(new NullPointerException()).when(kalendarRepository).findKalendarById(anyLong());

    try {
      kalendarService.deleteKalendar("",1l);
    } catch (ValidationException expected) {
      assertEquals(expectedMessage, expected.getMessage());
    }
  }

  @Test
  public void deleteGoogleCalendar_everythingIsOk() {
    Kalendar kalendar = new Kalendar();
    KalUser kalUser = new KalUser();
    kalendar.setUser(kalUser);
    GoogleAuth googleAuth = new GoogleAuth();

    when(kalendarRepository.findKalendarById(anyLong())).thenReturn(kalendar);
    when(kalUserRepository.findByClientToken(anyString())).thenReturn(kalUser);
    when(googleAuthRepository.findByUser_IdAndEmail(anyLong(), anyString())).thenReturn(googleAuth);
    try {
      kalendarService.deleteKalendar("",1l);
    } catch (ValidationException expected) {
      fail();
    }
  }

  private KalendarFromAndroid generateModelFromName(String name) {
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    kalendarFromAndroid.setCustomName(name);
    return kalendarFromAndroid;
  }
}