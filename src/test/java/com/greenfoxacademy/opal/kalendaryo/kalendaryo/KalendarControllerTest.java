package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers.KalendarController;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarListResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleCalendarService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.KalendarService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

public class KalendarControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mock;
    private HttpHeaders headers = new HttpHeaders();

    @Mock
    KalendarRepository kalendarRepository;

    @Mock
    KalUserRepository kalUserRepository;

    @Mock
    GoogleAuthRepository googleAuthRepository;

    @Mock
    KalendarService kalendarService;

    @Mock
    GoogleCalendarService googleCalendarService;

    @Mock
    AuthorizeKal authorizeKal;

    @InjectMocks
    KalendarController kalendarController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mock = MockMvcBuilders.standaloneSetup(kalendarController).build();

        headers.add("X-Client-Token", "+Q9rka18/XMiFLM3u8ainUIzU/o=");
    }

    @Test
    public void getCalendarShouldReturnHttp401WithoutClientToken() throws Exception {
        mock.perform(get("/calendar")
                .contentType(contentType))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void statusShouldBeOkWithClientToken() throws Exception {
        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk());
    }


    @Test
    public void outputAccountIdShouldReturnEmail() throws Exception {
        String expectedOutputAccountId = "test@email.com";

        KalendarResponse expectedKalendarResponse = new KalendarResponse();
        expectedKalendarResponse.setOutputCalendarId(expectedOutputAccountId);

        List<KalendarResponse> kalendarResponses = new ArrayList<>();
        kalendarResponses.add(expectedKalendarResponse);

        KalendarListResponse kalendarListResponse = new KalendarListResponse();
        kalendarListResponse.setKalendars(kalendarResponses);

        when(kalendarService.makeKalendarListResponse(anyString())).thenReturn(kalendarListResponse);

        mock.perform(get("/calendar")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kalendars[0].outputCalendarId", is(expectedOutputAccountId)));
    }

    @Test
    public void deleteKalendarSuccessfully() throws Exception {
        mock.perform(delete("/calendar/1")
                .contentType(contentType)
                .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteKalendarWithWrongId() throws Exception {
        doThrow(new ValidationException("No such kalendar in the database"))
            .when(kalendarService).deleteKalendar(anyString(), anyLong());

        mock.perform(delete("/calendar/2")
            .contentType(contentType)
            .headers(headers))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteKalendarWithWrongToken() throws Exception {
        doThrow(new ValidationException("No such user in the database"))
            .when(kalendarService).deleteKalendar(anyString(), anyLong());

        mock.perform(delete("/calendar/1")
            .contentType(contentType)
            .headers(headers))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteKalendarBadRequestWithoutToken() throws Exception {
        mock.perform(delete("/calendar/1")
            .contentType(contentType))
            .andExpect(status().isBadRequest());
    }
}