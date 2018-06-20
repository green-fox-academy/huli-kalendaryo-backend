package com.greenfoxacademy.kalendaryo.controllers;

import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.service.GoogleCalendarUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;


public class NotificationControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mock;
    private HttpHeaders headers = new HttpHeaders();

    @Mock
    GoogleCalendarUpdateService googleCalendarUpdateService;

    @Mock
    AuthAndUserService authAndUserService;

    @InjectMocks
    NotificationController notificationController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mock = MockMvcBuilders.standaloneSetup(notificationController).build();
    }


    @Test
    public void notificationEndPointExists() throws Exception {
        mock.perform(post("/notification"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void postNotificationGivesHttpStatusOk() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "1");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void channelIdMissing() throws Exception {
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "34");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void resourceIdMissing() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "34");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void resourceStateMissing() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Message-Number", "34");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void messageNumberMissing() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void resourceURIMissing() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "1");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void channelExpirationMissingAndStatusIsOk() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "1");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void channelTokenMissingAndStatusIsOk() throws Exception {
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "1");
        headers.add("X-Goog-Channel-Expiration", "1516102799000");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isOk());
    }
}
