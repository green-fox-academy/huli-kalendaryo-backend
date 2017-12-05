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
  Event event = new Event();



}
