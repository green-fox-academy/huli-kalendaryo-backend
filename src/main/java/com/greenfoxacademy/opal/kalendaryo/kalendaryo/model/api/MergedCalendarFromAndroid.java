package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.SharingOptions;
import sun.security.provider.SHA;

import java.util.List;

public class MergedCalendarFromAndroid {

  private String outputCalendarId;
  private CalendarId[] inputCalendarIds;

  public MergedCalendarFromAndroid() {
  }

  public MergedCalendarFromAndroid(String outputCalendarId, CalendarId[] inputCalendarIds) {
    this.outputCalendarId = outputCalendarId;
    this.inputCalendarIds = inputCalendarIds;
  }

  public String getOutputCalendarId() {
    return outputCalendarId;
  }

  public void setOutputCalendarId(String outputCalendarId) {
    this.outputCalendarId = outputCalendarId;
  }

  public CalendarId[] getInputCalendarIds() {
    return inputCalendarIds;
  }

  public void setInputCalendarIds(CalendarId[] inputCalendarIds) {
    this.inputCalendarIds = inputCalendarIds;
  }

}
