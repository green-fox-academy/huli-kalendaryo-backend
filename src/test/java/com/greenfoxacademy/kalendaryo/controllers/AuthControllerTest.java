package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.api.KalendarListResponse;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.service.GoogleCalendarService;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.kalendaryo.model.api.KalendarResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

public class AuthControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  private MockMvc mock;
  private HttpHeaders headers = new HttpHeaders();

  @Mock
  AuthAndUserService authAndUserService;

  @Mock
  KalUserRepository kalUserRepository;

  @Mock
  GoogleAuthRepository googleAuthRepository;

  @InjectMocks
  AuthController authController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mock = MockMvcBuilders.standaloneSetup(authController).build();
    headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");
  }

  @Test
  public void notificationEndPointExists() throws Exception {
    mock.perform(post("/auth"))
            .andExpect(status().isOk());
  }
}