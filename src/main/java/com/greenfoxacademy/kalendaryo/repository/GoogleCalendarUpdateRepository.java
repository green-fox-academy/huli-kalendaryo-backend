package com.greenfoxacademy.kalendaryo.repository;

import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendarUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleCalendarUpdateRepository extends CrudRepository<GoogleCalendarUpdate, Long> {

}
