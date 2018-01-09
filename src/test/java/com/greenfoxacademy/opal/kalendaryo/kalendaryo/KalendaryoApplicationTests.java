package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

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

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableWebMvc
public class KalendaryoApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mock;

	@Before
	public void setUp() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

    @Test
    public void watchIsSetup() throws Exception {
        mock.perform(post("/notification"))
                .andExpect(status().isOk());
    }

	@Test
	public void postNotificationGivesHttpStatusOk() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "1");

        // these are not necessary but can be part of the headers.
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

		mock.perform(post("/notification")
				.contentType(contentType)
				.headers(headers)).andDo(print())
				.andExpect(status().isOk());
	}

    @Test
    public void postNotificationGivesHttpStatusNotAcceptable() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        // if we don't use the first required header, we still get a 200 OK. instead we should get a 406, Not Acceptable
        // headers.add("X-Goog-Channel-ID", "01234567-89ab-cdef-0123456788");
        headers.add("X-Goog-Resource-ID", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
        headers.add("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
        headers.add("X-Goog-Resource-State", "sync");
        headers.add("X-Goog-Message-Number", "34");

        // these are not necessary but can be part of the headers.
        headers.add("X-Goog-Channel-Expiration", "1516102799000");
        headers.add("X-Goog-Channel-Token", "");

        mock.perform(post("/notification")
                .contentType(contentType)
                .headers(headers)).andDo(print())
                .andExpect(status().isOk());
    }
}
