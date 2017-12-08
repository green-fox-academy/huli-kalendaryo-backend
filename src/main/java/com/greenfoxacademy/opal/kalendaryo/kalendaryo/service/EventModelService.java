package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.EventModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventModelService {

    @Autowired
    EventModelRepository eventModelRepository;

    

    public void saveEventModel(EventModel eventModel) {
        eventModelRepository.save(eventModel);
    }
}
