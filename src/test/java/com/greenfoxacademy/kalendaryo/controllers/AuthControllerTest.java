package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    headers.add("email", "test-email@gmail.com");
  }

  @Test
  public void getAuthShouldReturnHttp401WithoutClientToken() throws Exception {
    mock.perform(get("/auth")
            .contentType(contentType))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getStatusShouldBeOkWithClientToken() throws Exception {
    mock.perform(get("/auth")
            .contentType(contentType)
            .headers(headers))
            .andExpect(status().isOk());
  }

  @Test
  public void postAuthShouldReturnHttp401WithoutClientToken() throws Exception {
    mock.perform(post("/auth")
            .contentType(contentType))
            .andExpect(status().is4xxClientError());
  }
}