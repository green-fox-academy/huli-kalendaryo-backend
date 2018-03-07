package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.EventResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface EventResponseRepository extends CrudRepository<EventResponse, Long> {
}
