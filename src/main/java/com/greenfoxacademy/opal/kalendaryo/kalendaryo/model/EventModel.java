package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventModel {

  @Id
  private String event_ID;
  private String name;
  private String description;
  private String summary;



  @ManyToOne
  @JoinColumn(name = "calendar_ID")
  Calendar calendar;

  public EventModel(String event_ID, String name, String description,
      Calendar calendar,String summary) {
    this.event_ID = event_ID;
    this.name = name;
    this.description = description;
    this.calendar = calendar;
    this.summary = summary;
  }

  public EventModel() {
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getEvent_ID() {
    return event_ID;
  }

  public void setEvent_ID(String event_ID) {
    this.event_ID = event_ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }
}
