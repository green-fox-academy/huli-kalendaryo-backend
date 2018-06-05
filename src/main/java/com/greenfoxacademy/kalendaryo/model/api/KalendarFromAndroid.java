package com.greenfoxacademy.kalendaryo.model.api;

public class KalendarFromAndroid {

  private String outputGoogleAuthId;
  private GoogleCalendarFromAndroid[] inputGoogleCalendars;
  private String customName;

  public KalendarFromAndroid() {
  }

  public KalendarFromAndroid(String outputGoogleAuthId, GoogleCalendarFromAndroid[] inputGoogleCalendars) {
    this.outputGoogleAuthId = outputGoogleAuthId;
    this.inputGoogleCalendars = inputGoogleCalendars;
    this.customName = customName;
  }

  public String getOutputGoogleAuthId() {
    return outputGoogleAuthId;
  }

  public void setOutputGoogleAuthId(String outputGoogleAuthId) {
    this.outputGoogleAuthId = outputGoogleAuthId;
  }

  public GoogleCalendarFromAndroid[] getInputGoogleCalendars() {
    return inputGoogleCalendars;
  }

  public void setInputGoogleCalendars(GoogleCalendarFromAndroid[] inputGoogleCalendars) {
    this.inputGoogleCalendars = inputGoogleCalendars;
  }

  public String getCustomName() {
    return customName;
  }

  public void setCustomName(String customName) {
    this.customName = customName;
  }
}
