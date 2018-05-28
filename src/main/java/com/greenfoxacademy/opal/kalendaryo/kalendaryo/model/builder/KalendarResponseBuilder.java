package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.builder;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;

import java.util.List;

public class KalendarResponseBuilder {

  private String outputGoogleAuthId;
  private String outputCalendarId;
  private List<String> inputGoogleCalendars;
  private long id;

  public KalendarResponseBuilder() {
  }

  public KalendarResponseBuilder setOutputCalendarId(String outputCalendarId) {
    this.outputCalendarId = outputCalendarId;
    return this;
  }

  public KalendarResponseBuilder setOutputGoogleAuthId(String outputGoogleAuthId) {
    this.outputGoogleAuthId = outputGoogleAuthId;
    return this;
  }

  public KalendarResponseBuilder setInputGoogleCalendars(List<String> inputGoogleCalendars) {
    this.inputGoogleCalendars = inputGoogleCalendars;
    return this;
  }

  public KalendarResponseBuilder setId(long id) {
    this.id = id;
    return this;
  }

  public KalendarResponse build() {
    return  new KalendarResponse(outputGoogleAuthId, outputCalendarId, inputGoogleCalendars, id);
  }
}
