package com.greenfoxacademy.kalendaryo.model.api;

public class GoogleCalendarFromAndroid {
  private String id;
  private String summary;
  private String sharingOption;

  public GoogleCalendarFromAndroid() {
  }

  public GoogleCalendarFromAndroid(String id, String summary, String sharingOptions) {
    this.id = id;
    this.summary = summary;
    this.sharingOption = sharingOptions;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getSharingOption() {
    return sharingOption;
  }

  public void setSharingOption(String sharingOptions) {
    this.sharingOption = sharingOptions;
  }
}
