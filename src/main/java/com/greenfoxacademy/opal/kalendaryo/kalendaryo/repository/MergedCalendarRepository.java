package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MergedCalendarRepository extends CrudRepository<MergedCalendar,Long> {
    List<MergedCalendar>findMergedCalendarsByUsername(UserModel user);



}
