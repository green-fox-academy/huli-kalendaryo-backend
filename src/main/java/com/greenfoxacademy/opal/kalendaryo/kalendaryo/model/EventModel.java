package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventModel {

  @Id
  private String event_id;
  private String name;
  private String description;
  private String summary;



  @ManyToOne
  @JoinColumn(name = "calendar_id")
  Calendar calendar;

  public EventModel(String event_id, String name, String description,
      Calendar calendar,String summary) {
    this.event_id = event_id;
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

  public String getEvent_id() {
    return event_id;
  }

  public void setEvent_id(String event_id) {
    this.event_id = event_id;
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
