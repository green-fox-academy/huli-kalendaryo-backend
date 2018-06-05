package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.AuthResponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableWebMvc
public class AuthControllerTest {

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
    public void shouldReturnHTTPStatusIsCreated () throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("X-Client-Token", "rut3464ts");

        String expectedResult = "{\"userId\": 4,\"clientToken\":\"my_access32\",\"userEmail\":\"jimbo@jimbo.com\",\"accessToken\":\"scxc230112\"}";

        mock.perform(post("/auth")
                .contentType(contentType)
                .content(expectedResult)
                .headers(header)).andDo(print())
                .andExpect(status().isCreated());
    }
}