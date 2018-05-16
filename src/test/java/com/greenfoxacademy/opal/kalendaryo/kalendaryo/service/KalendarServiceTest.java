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

  private MockMvc mock;

  private String clientToken;
  private KalendarFromAndroid kalendarFromAndroid;
  private Kalendar kalendar;

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
    mock = MockMvcBuilders.standaloneSetup(kalendarService).build();
    kalendarFromAndroid = new KalendarFromAndroid();
    kalendar = new Kalendar();
    clientToken = "sPnRiVAq6n8Nq/SmeB9VHSnBsI4=";
  }

  @Test
  public void checkIfKalendarNameEqualsCustomName() {
    kalendarFromAndroid.setCustomName("Hello");
    assertEquals("Hello", kalendarService.setKalendarAttribute(kalendar, kalendarFromAndroid, clientToken).getName());
  }

  @Test
  public void checkIfNameIsNotNullIfCustomNameIsNotEntered() {
    kalendarFromAndroid.setCustomName("");
    assertTrue(kalendarService.setKalendarAttribute(kalendar, kalendarFromAndroid, clientToken)
            .getName().length() > 0);
  }
}