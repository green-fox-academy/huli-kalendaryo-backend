package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class MergedCalendarResponse {

  private List<MergedCalendar> mergedCalendars;

  public MergedCalendarResponse(){
  }

  public List<MergedCalendar> getMergedCalendars() {
    return mergedCalendars;
  }

  public void setMergedCalendars(
      List<MergedCalendar> mergedCalendars) {
    this.mergedCalendars = mergedCalendars;
  }



}
