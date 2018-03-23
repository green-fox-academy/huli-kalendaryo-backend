package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KalendarRepository extends CrudRepository<Kalendar,Long> {

    List<Kalendar> findKalendarsByUser(KalUser kalUser);

    Kalendar findByOutputCalendarId(String outputCalendarId);
}
