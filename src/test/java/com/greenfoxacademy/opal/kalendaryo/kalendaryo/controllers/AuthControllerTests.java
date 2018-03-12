package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableWebMvc
public class AuthControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpHeaders header = new HttpHeaders();

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void authEndPointExists() throws Exception {
        mockMvc.perform(get("/auth")
                .contentType(contentType))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnHTTPStatusOK() throws Exception {
        header.add("X-Client-Token", "SXkTebWw0cInKXb0U6/Nn66KbKo=");

        mockMvc.perform(get("/auth")
                .contentType(contentType)
                .headers(header))
                .andExpect(status().isOk());
    }

    @Test
    public void emailShouldReturnString() throws Exception {
        header.add("X-Client-Token", "SXkTebWw0cInKXb0U6/Nn66KbKo=");

        mockMvc.perform(get("/auth")
                .contentType(contentType)
                .headers(header))
                .andExpect(jsonPath("$.email", is(String.class)));
    }

    @Test
    public void accessTokenShouldReturnString() throws Exception {
        header.add("X-Client-Token", "SXkTebWw0cInKXb0U6/Nn66KbKo=");

        mockMvc.perform(get("/auth")
                .contentType(contentType)
                .headers(header))
                .andExpect(jsonPath("$.authModels[0].accessToken", is(String.class)));
    }

    @Test
    public void authModelEmailShouldReturnString() throws Exception {
        header.add("X-Client-Token", "SXkTebWw0cInKXb0U6/Nn66KbKo=");

        mockMvc.perform(get("/auth")
                .contentType(contentType)
                .headers(header))
                .andExpect(jsonPath("$.authModels[0].email", is(String.class)));
    }

    @Test
    public void authModelEmailShouldReturnUserModelEmail() throws Exception {
        header.add("X-Client-Token", "SXkTebWw0cInKXb0U6/Nn66KbKo=");

        mockMvc.perform(get("/auth")
                .contentType(contentType)
                .headers(header))
                .andExpect(jsonPath("$.authModels[0].email", is("hieuci88@gmail.com")));
    }
}
