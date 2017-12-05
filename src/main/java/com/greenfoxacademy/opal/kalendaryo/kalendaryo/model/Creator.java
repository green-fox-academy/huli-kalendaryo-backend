package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Creator {


  @Id
  private String id;
  private String displayname;
  private String email;

  @OneToMany
  List<EventModel> eventsList;


  public Creator(String id, String displayname, String email,
      List<EventModel> eventsList) {
    this.id = id;
    this.displayname = displayname;
    this.email = email;
    this.eventsList = eventsList;
  }

  public Creator() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisplayname() {
    return displayname;
  }

  public void setDisplayname(String displayname) {
    this.displayname = displayname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<EventModel> getEventsList() {
    return eventsList;
  }

  public void setEventsList(
      List<EventModel> eventsList) {
    this.eventsList = eventsList;
  }
}
