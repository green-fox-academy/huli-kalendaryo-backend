package com.greenfoxacademy.opal.kalendaryo.kalendaryo.testEnvironment;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Profile("test")
public class TestCalendarIdService {

    @Autowired
    CalendarIdRepository calendarIdRepository;

    public void saveCalendarId(CalendarId calendarId) {
        calendarIdRepository.save(calendarId);
    }
}
