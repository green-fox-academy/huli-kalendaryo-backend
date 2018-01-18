package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CalendarId {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    AuthModel authModel;

    @ManyToOne(cascade = CascadeType.ALL)
    MergedCalendar mergedCalendar;

    public CalendarId(String id,
        AuthModel authModel,
        MergedCalendar mergedCalendar) {
        this.id = id;
        this.authModel = authModel;
        this.mergedCalendar = mergedCalendar;
    }

    public CalendarId() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public MergedCalendar getMergedCalendar() {
        return mergedCalendar;
    }

    public void setMergedCalendar(
        MergedCalendar mergedCalendar) {
        this.mergedCalendar = mergedCalendar;
    }
}
