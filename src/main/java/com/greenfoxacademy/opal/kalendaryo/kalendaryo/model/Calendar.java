package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

  @Id
  private String id;
  private String name;
  private String kind;
  private String summary;


  @OneToMany
  @JoinColumn(name = "id")
  List<Event> eventList;

  public Calendar(String id, String name, String kind, String summary) {
    this.id = id;
    this.name = name;
    this.kind = kind;
    this.summary = summary;
  }

  public Calendar() {
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
