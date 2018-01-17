package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import java.util.List;

public class MergedCalendarFromAndroid {

  private String outputCalendarId;
  private List<String> inputCalendaIds;

  public MergedCalendarFromAndroid() {
  }

  public String getOutputCalendarId() {
    return outputCalendarId;
  }

  public void setOutputCalendarId(String outputCalendarId) {
    this.outputCalendarId = outputCalendarId;
  }

  public List<String> getInputCalendaIds() {
    return inputCalendaIds;
  }

  public void setInputCalendaIds(List<String> inputCalendaIds) {
    this.inputCalendaIds = inputCalendaIds;
  }
}
