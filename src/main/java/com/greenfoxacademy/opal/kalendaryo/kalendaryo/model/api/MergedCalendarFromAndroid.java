package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class MergedCalendarFromAndroid {

  private String outputCalendarId;
  private String[] inputCalendarIds;

  public MergedCalendarFromAndroid() {
  }

  public MergedCalendarFromAndroid(String outputCalendarId, String[] inputCalendarIds) {
    this.outputCalendarId = outputCalendarId;
    this.inputCalendarIds = inputCalendarIds;
  }

  public String getOutputCalendarId() {
    return outputCalendarId;
  }

  public void setOutputCalendarId(String outputCalendarId) {
    this.outputCalendarId = outputCalendarId;
  }

  public String[] getInputCalendarIds() {
    return inputCalendarIds;
  }

  public void setInputCalendarIds(String[] inputCalendarIds) {
    this.inputCalendarIds = inputCalendarIds;
  }
}
