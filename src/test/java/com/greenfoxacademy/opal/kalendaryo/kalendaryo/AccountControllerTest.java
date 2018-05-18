package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers.AccountController;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

public class AccountControllerTest {
  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  private MockMvc mock;
  private HttpHeaders headers = new HttpHeaders();

  @Mock
  AuthAndUserService authAndUserService;

  @InjectMocks
  AccountController accountController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mock = MockMvcBuilders.standaloneSetup(accountController).build();
    headers.add("X-Client-Token", "Dm1IyAVxRrDrTQKtQH32fCvvyjY=");
  }

  @Test
  public void getAccountShouldReturnHttp401WithoutClientToken() throws Exception {
    mock.perform(get("/account")
            .contentType(contentType))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getAccountstatusShouldBe200WithClientToken() throws Exception {
    mock.perform(get("/account")
            .contentType(contentType)
            .headers(headers))
            .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void deleteAccountstatusShouldBe200WithClientToken() throws Exception {
    mock.perform(delete("/account")
            .contentType(contentType))
            .andExpect(status().is4xxClientError());
  }
}

