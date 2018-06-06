package com.greenfoxacademy.kalendaryo.model.api;

public class GoogleCalendarFromAndroid {
  private String id;
  private String summary;
  private String sharingOptions;
  private String email;

  public GoogleCalendarFromAndroid() {
  }

  public GoogleCalendarFromAndroid(String id, String summary, String sharingOptions) {
    this.id = id;
    this.summary = summary;
    this.sharingOptions = sharingOptions;
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

  public String getSharingOptions() {
    return sharingOptions;
  }

  public void setSharingOptions(String sharingOptions) {
    this.sharingOptions = sharingOptions;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
