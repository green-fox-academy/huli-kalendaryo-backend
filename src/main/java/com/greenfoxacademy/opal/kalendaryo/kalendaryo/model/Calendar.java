package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

  @Id
  private String calendar_id;
  private String name;
  private String kind;
  private String summary;


  @OneToMany
  @JoinColumn(name = "event_id")
  List<EventModel> eventModelList;

  public Calendar(String calendar_id, String name, String kind, String summary) {
    this.calendar_id = calendar_id;
    this.name = name;
    this.kind = kind;
    this.summary = summary;
  }

  public Calendar() {
  }


  public String getCalendar_id() {
    return calendar_id;
  }

  public void setCalendar_id(String calendar_id) {
    this.calendar_id = calendar_id;
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
