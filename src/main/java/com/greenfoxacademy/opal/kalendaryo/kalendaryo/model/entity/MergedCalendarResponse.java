package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import java.util.List;

public class MergedCalendarResponse {

  private List<MergedCalendar> mergedCalendars;

  public MergedCalendarResponse() {
  }

  public MergedCalendarResponse(List<MergedCalendar> mergedCalendarByOutputCalendarId) {
  }

  public List<MergedCalendar> getMergedCalendar() {
    return mergedCalendars;
  }

  public void setMergedCalendar(
      List<MergedCalendar> mergedCalendar) {
    this.mergedCalendars = mergedCalendars;
  }
}
