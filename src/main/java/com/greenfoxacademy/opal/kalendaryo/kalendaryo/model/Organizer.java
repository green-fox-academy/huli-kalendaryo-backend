package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Organizer {

  @Id
  private String id;
  private String email;
  private String displayname;

  @OneToOne
  EventModel eventModel;

  public Organizer(String id, String email, String displayname,
      EventModel eventModel) {
    this.id = id;
    this.email = email;
    this.displayname = displayname;
    this.eventModel = eventModel;
  }

  public Organizer() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayname() {
    return displayname;
  }

  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }

  public EventModel getEventModel() {
    return eventModel;
  }

  public void setEventModel(EventModel eventModel) {
    this.eventModel = eventModel;
  }
}
