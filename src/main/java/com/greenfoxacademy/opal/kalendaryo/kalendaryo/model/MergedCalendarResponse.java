package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.ArrayList;
import java.util.List;

public class MergedCalendarResponse {

  private List<MergedCalendar> mergedCalendars;

  public List<MergedCalendar> getMergedCalendars() {
    return mergedCalendars;
  }

  public void setMergedCalendars(
      List<MergedCalendar> mergedCalendars) {
    this.mergedCalendars = mergedCalendars;
  }
  
  public void getAllCalendars(List<MergedCalendar> mergedCalendars) {
    List<MergedCalendar> fullList = new ArrayList<>();
    for (int i = 0; i < mergedCalendars.size() ; i++) {
      fullList.add(mergedCalendars.add();
    }
    
    return null;
  }
}
