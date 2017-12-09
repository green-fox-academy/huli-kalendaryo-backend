package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EventModel {

  @Id
  private String eventId;
  private String name;
  private String description;
  private String summary;



  @ManyToOne
  @JoinColumn(name = "calendar_id")
  Calendar calendar;

  public EventModel(String eventId, String name, String description,
      Calendar calendar,String summary) {
    this.eventId = eventId;
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

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
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
