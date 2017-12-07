package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CalUser {

  @Id
  private String id;
  private String displayName;
  private String email;

  @OneToMany
  List<EventModel> eventsList;

  public CalUser(String id, String displayName, String email) {
    this.id = id;
    this.displayName = displayName;
    this.email = email;
  }

  public CalUser() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}