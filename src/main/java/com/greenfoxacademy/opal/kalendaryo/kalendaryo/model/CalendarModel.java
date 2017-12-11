package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CalendarModel {

  @Id
  private String calendarModelId;
  private String name;
  private String kind;
  private String summary;


  @OneToMany(mappedBy = "calendarModel")
  List<EventModel> eventModelList;


  public CalendarModel(String calendarModelId, String name, String kind, String summary) {
    this.calendarModelId = calendarModelId;
    this.name = name;
    this.kind = kind;
    this.summary = summary;
  }

  public CalendarModel() {
  }


  public String getCalendarModelId() {
    return calendarModelId;
  }

  public void setCalendarModelId(String calendarModelId) {
    this.calendarModelId = calendarModelId;
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

  public List<EventModel> getEventModelList() {
    return eventModelList;
  }

  public void setEventModelList(List<EventModel> eventModelList) {
    this.eventModelList = eventModelList;
  }
}
