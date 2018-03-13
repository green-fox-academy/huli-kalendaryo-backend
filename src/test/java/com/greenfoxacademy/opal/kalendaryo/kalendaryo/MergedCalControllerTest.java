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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

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
    public void getCalendarShouldReturnHttp401WithoutClientToken() throws Exception {
        mock.perform(get("/calendar")
                .contentType(contentType))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void statusShouldBeOkWithClientToken() throws Exception {
        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void outputAccountIdShouldReturnString() throws Exception {
        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mergedCalendars[0].outputAccountId", is(String.class)));
    }

    @Test
    public void outputAccountIdShouldReturnEmail() throws Exception {
        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mergedCalendars[0].outputAccountId", is("test@email.com")));
    }

    @Test
    public void inputCalendarIdsShouldReturnList() throws Exception {
        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");

        List<String> testList = new ArrayList<>();

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mergedCalendars[0].inputCalendarIds", is(testList)));
    }

    @Test
    public void inputCalendarIdsShouldReturnListOfMergedCals() throws Exception {
        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");

        List<String> testList = new ArrayList<>(Arrays.asList("mergedCalendar1", "mergedCalendar2"));

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mergedCalendars[0].inputCalendarIds", is(testList)));
    }
}