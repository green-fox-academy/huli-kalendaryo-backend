package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.google.api.client.googleapis.testing.TestUtils;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableWebMvc
public class MergedCalControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mock;
    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void calendarEndPointExists() throws Exception {
        mock.perform(post("/calendar"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postCalendarOk() throws Exception {
        headers.add("X-Client-Token", "${CLIENT_SECRET}");
        headers.add("accept", "application/json");
        String content = "{\"outputAccountId\": \"string\",\"inputCalendarIds\": [\"foo123\", \"bar456\", \"baz789\"]}";

        mock.perform(post("/calendar")
                .contentType(contentType)
                .content(content)
                .headers(headers))
                .andExpect(status().isOk());
    }
}
