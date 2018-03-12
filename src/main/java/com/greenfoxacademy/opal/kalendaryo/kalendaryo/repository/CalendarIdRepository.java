package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarIdRepository extends CrudRepository<CalendarId, String> {
    List<CalendarId> findCalendarIdsByKalendar(Kalendar kalendarId);
}
