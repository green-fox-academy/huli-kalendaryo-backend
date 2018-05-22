package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

public class KalendarServiceTest {

  @Mock
  KalUserRepository kalUserRepository;

  @Mock
  Authorization authorization;

  @Mock
  GoogleCalendarRepository googleCalendarRepository;

  @Mock
  KalendarRepository kalendarRepository;

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

  private KalendarFromAndroid generateModelFromName(String name) {
    KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
    kalendarFromAndroid.setCustomName(name);
    return kalendarFromAndroid;
  }
}