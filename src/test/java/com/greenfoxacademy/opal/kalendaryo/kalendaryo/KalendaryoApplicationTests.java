package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
	public void postNotificationgivesHttpStatusOk() throws Exception {
		JSONObject response = new JSONObject();

		response.put("kind", "api#channel");
		response.put("id", "01234567-89ab-cdef-0123456788");
		response.put("resourceId", "WDOXEjsdYtXzZHq93mDhG6dfTrg");
		response.put("resourceUri", "https://www.googleapis.com/calendar/v3/calendars/huli.opal.kalendaryo@gmail.com/events?maxResults=250&alt=json");
		response.put("expiration", "1515496369000");

		mock.perform(post("/notification")
				.contentType(MediaType.APPLICATION_JSON)
				.content(response.toString()))
				.andExpect(status().isOk());


	}
}
