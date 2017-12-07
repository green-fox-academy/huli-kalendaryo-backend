package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

  @Id
  private String calendarId;
  private String name;
  private String kind;
  private String summary;


  @OneToMany
  @JoinColumn(name = "event_id")
  List<EventModel> eventModelList;

  public Calendar(String calendarId, String name, String kind, String summary) {
    this.calendarId = calendarId;
    this.name = name;
    this.kind = kind;
    this.summary = summary;
  }

  public Calendar() {
  }


  public String getCalendarId() {
    return calendarId;
  }

  public void setCalendarId(String calendarId) {
    this.calendarId = calendarId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }
}
