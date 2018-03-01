package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarIdService {

    @Autowired
    CalendarIdRepository calendarIdRepository;

    public void save(CalendarId calendarId) {
        calendarIdRepository.save(calendarId);
    }
}
