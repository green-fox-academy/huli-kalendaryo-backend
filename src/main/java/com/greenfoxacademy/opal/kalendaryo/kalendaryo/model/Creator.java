package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Creator {


  @Id
  private String id;
  private String displayname;
  private String email;

  @OneToMany(fetch = FetchType.EAGER)
  List<Event> eventsList;




}
