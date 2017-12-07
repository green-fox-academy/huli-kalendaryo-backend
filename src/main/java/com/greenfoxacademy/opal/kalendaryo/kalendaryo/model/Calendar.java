package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

  @Id
  private String calendar_ID;
  private String name;
  private String kind;
  private String summary;


  @OneToMany
  @JoinColumn(name = "event_ID")
  List<EventModel> eventModelList;

  public Calendar(String calendar_ID, String name, String kind, String summary) {
    this.calendar_ID = calendar_ID;
    this.name = name;
    this.kind = kind;
    this.summary = summary;
  }

  public Calendar() {
  }


  public String getCalendar_ID() {
    return calendar_ID;
  }

  public void setCalendar_ID(String calendar_ID) {
    this.calendar_ID = calendar_ID;
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
