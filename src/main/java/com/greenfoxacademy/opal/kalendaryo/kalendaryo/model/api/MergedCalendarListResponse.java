package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import java.util.ArrayList;
import java.util.List;

public class MergedCalendarListResponse {

  private List<MergedCalendarResponse> calendarResponseList;

  public MergedCalendarListResponse() {
  }

  public List<MergedCalendarResponse> getCalendarResponseList() {
    return calendarResponseList;
  }

  public void setCalendarResponseList(
      List<MergedCalendarResponse> calendarResponseList) {
    this.calendarResponseList = calendarResponseList;
  }

  public void setMergedCalendarResponse(List<MergedCalendar> mergedCalendars) {
    for (int i = 0; i < mergedCalendars.size(); i++) {
      MergedCalendarResponse mergedCalendarResponse = new MergedCalendarResponse();
      mergedCalendarResponse.setOutputAccountId(mergedCalendars.get(i).getOutputAccount());
      mergedCalendarResponse.setOutputCalendarId(mergedCalendars.get(i).getOutputCalendarId());
      mergedCalendarResponse.setInputCalendarId((setToStringCalendarIds(mergedCalendars.get(i))));
      this.calendarResponseList.add(mergedCalendarResponse);
    }
  }

  List<String> setToStringCalendarIds(MergedCalendar mergedCalendar) {
    List<String> CalendarIDstoString = new ArrayList<>();
    for (int i = 0; i <mergedCalendar.getCalendarIds().size(); i++) {
      CalendarIDstoString.add(mergedCalendar.getCalendarIds().get(i).toString());
    }
    return CalendarIDstoString;
  }
}
