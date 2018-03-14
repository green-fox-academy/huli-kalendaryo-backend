package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendarUpdate;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarUpdateService {

    @Autowired
    GoogleCalendarUpdateRepository googleCalendarUpdateRepository;

    public void saveEventResponse(GoogleCalendarUpdate googleCalendarUpdate) {
        googleCalendarUpdateRepository.save(googleCalendarUpdate);
    }

    public Iterable<GoogleCalendarUpdate> findAllEventResponse() {
        return googleCalendarUpdateRepository.findAll();
    }

}
