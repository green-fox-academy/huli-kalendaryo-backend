package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleCalendarRepository extends CrudRepository<GoogleCalendar, String> {

    List<GoogleCalendar> findGoogleCalendarsByKalendar(Kalendar kalendar);
    void deleteAllByKalendar_Id(long id);
}
