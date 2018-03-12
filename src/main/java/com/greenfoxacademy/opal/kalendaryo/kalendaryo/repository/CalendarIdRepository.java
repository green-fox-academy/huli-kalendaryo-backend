package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarIdRepository extends CrudRepository<CalendarId, String> {
<<<<<<< HEAD
=======
    List<CalendarId> findCalendarIdsByMergedCalendar(MergedCalendar mergedCalendarId);
>>>>>>> 0b791d93149c700f184a565d214e2cbdce445ea4
}
