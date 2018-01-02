package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CalendarId {
    @Id
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    AuthModel authModel;

    @ManyToOne(cascade = CascadeType.ALL)
    MergedCalendar mergedCalendar;

}
