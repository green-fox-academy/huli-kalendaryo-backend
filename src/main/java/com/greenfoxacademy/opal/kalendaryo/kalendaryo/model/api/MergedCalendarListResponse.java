package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import java.util.ArrayList;
import java.util.List;

public class MergedCalendarListResponse {

  private List<MergedCalendarResponse> mergedCalendars;

  public MergedCalendarListResponse() {
  }

  public List<MergedCalendarResponse> getMergedCalendars() {
    return mergedCalendars;
  }

  public void setMergedCalendars(List<MergedCalendarResponse> mergedCalendars) {
    this.mergedCalendars = mergedCalendars;
  }

  public void setMergedCalendarResponse(List<MergedCalendar> mergedCalendars) {
    for (int i = 0; i < mergedCalendars.size(); i++) {
      MergedCalendarResponse mergedCalendarResponse = new MergedCalendarResponse();
      mergedCalendarResponse.setOutputAccountId(mergedCalendars.get(i).getOutputAccount());
      mergedCalendarResponse.setOutputCalendarId(mergedCalendars.get(i).getOutputCalendarId());
      mergedCalendarResponse.setInputCalendarIds((setToStringCalendarIds(mergedCalendars.get(i))));
      this.mergedCalendars.add(mergedCalendarResponse);
    }
  }

  List<String> setToStringCalendarIds(MergedCalendar mergedCalendar) {
    List<String> CalendarIDsToString = new ArrayList<>();
    for (int i = 0; i < mergedCalendar.getCalendarIds().size(); i++) {
      CalendarIDsToString.add(mergedCalendar.getCalendarIds().get(i).toString());
    }
    return CalendarIDsToString;
  }
}
