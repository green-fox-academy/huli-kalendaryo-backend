package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Calendar {

  @Id
  private String id;
  private String name;
  private String kind;
  private String desctiption;

  @OneToMany
  Event envet = new Event();

  public Calendar(String id, String name, String kind, String desctiption) {
    this.id = id;
    this.name = name;
    this.kind = kind;
    this.desctiption = desctiption;
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

  public String getDesctiption() {
    return desctiption;
  }

  public void setDesctiption(String desctiption) {
    this.desctiption = desctiption;
  }
}
