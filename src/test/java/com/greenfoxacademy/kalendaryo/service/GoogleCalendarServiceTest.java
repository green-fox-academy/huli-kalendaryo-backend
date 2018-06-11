package com.greenfoxacademy.kalendaryo.service;

import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class GoogleCalendarServiceTest {


    @Mock
    GoogleCalendarRepository googleCalendarRepositoryMock;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveGoogleCalendarTest() {
        GoogleCalendar googleCalendar = new GoogleCalendar();
       when(googleCalendarRepositoryMock.save(any(GoogleCalendar.class))).thenReturn(googleCalendar);
         }

    @Test
    public void setGoogleCalendar() {
    }
}