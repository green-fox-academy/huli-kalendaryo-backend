package com.greenfoxacademy.kalendaryo.model.api;

import java.util.Arrays;

public class KalendarFromAndroid {

  private String outputGoogleAuthId;
  private String[] inputGoogleCalendars;
  private String customName;

  public KalendarFromAndroid() {
  }

  public KalendarFromAndroid(String outputGoogleAuthId, String[] inputGoogleCalendars) {
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

  public String[] getInputGoogleCalendars() {
    return inputGoogleCalendars;
  }

  public void setInputGoogleCalendars(String[] inputGoogleCalendars) {
    this.inputGoogleCalendars = inputGoogleCalendars;
  }

  public String getCustomName() {
    return customName;
  }

  public void setCustomName(String customName) {
    this.customName = customName;
  }

  @Override
  public String toString() {
    return "##### KalendarFromAndroid{" +
      "inputGoogleCalendars=" + Arrays.toString(inputGoogleCalendars) + "#####" +
      '}';
  }
}

