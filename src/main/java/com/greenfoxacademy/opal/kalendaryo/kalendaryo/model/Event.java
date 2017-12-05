package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import com.google.api.client.util.DateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event {

  @Id
  private String id;
  private String name;
  private DateTime date;
  private String description;
  private String summary;



  @ManyToOne
  @JoinColumn(name = "id")
  Calendar calendar;

  public Event(String id, String name, DateTime date, String description,
      Calendar calendar,String summary) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.description = description;
    this.calendar = calendar;
    this.summary = summary;
  }

  public Event() {
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
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
