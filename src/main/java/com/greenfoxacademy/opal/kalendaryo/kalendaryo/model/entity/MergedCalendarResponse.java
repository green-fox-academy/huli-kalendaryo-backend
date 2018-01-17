package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import java.util.List;

public class MergedCalendarResponse {

  private List<MergedCalendar> mergedCalendar;

  public MergedCalendarResponse() {
  }

  public List<MergedCalendar> getMergedCalendar() {
    return mergedCalendar;
  }

  public void setMergedCalendar(
      List<MergedCalendar> mergedCalendar) {
    this.mergedCalendar = mergedCalendar;
  }
}
