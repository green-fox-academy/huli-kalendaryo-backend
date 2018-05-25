package com.greenfoxacademy.kalendaryo.service;

import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarUpdateRepository;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendarUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarUpdateService {

    @Autowired
    GoogleCalendarUpdateRepository googleCalendarUpdateRepository;

    public void saveGoogleCalendarUpdate(GoogleCalendarUpdate googleCalendarUpdate) {
        googleCalendarUpdateRepository.save(googleCalendarUpdate);
    }

    public Iterable<GoogleCalendarUpdate> findAllGoogleCalendarUpdate() {
        return googleCalendarUpdateRepository.findAll();
    }
}
