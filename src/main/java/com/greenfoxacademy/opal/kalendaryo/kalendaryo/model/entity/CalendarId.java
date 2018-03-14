package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.*;

@Entity
public class CalendarId {

    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="auth_model_email")
    AuthModel authModel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merged_calendar_id")
    MergedCalendar mergedCalendar;

    String[] sharingOptions;

    public CalendarId(String id, AuthModel authModel, MergedCalendar mergedCalendar, String[] sharingOptions) {
        this.id = id;
        this.authModel = authModel;
        this.mergedCalendar = mergedCalendar;
        this.sharingOptions = sharingOptions;
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

    public void setMergedCalendar(MergedCalendar mergedCalendar) {
        this.mergedCalendar = mergedCalendar;
    }

    public void setSharingOptions(String[] sharingOptions) {
        this.sharingOptions = sharingOptions;
    }

    public String[] getSharingOptions() {
        return sharingOptions;
    }
}
