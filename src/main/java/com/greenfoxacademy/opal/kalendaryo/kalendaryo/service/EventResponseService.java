package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.EventResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventResponseService {

    @Autowired
    EventResponseRepository eventResponseRepository;

    public void saveEventResponse(EventResponse eventResponse) {
        eventResponseRepository.save(eventResponse);
    }

    public Iterable<EventResponse> findAllEventResponse() {
        return eventResponseRepository.findAll();
    }

}
