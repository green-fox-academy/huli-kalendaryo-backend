package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import com.google.api.client.util.DateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {

  @Id
  private String id;
  private String name;
  private DateTime date;
  private String description;



  @ManyToOne(fetch = FetchType.EAGER)
  Calendar calendar = new Calendar();

}
