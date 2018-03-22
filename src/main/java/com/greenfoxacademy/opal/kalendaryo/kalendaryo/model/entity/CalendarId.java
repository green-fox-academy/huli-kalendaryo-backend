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

    @Transient
    SharingOptions sharingOptions;

    @Column
    Boolean sharing_title;
    @Column
    Boolean sharing_location;
    @Column
    Boolean sharing_description;
    @Column
    Boolean sharing_organizer;
    @Column
    Boolean sharing_attendants;

    public CalendarId(String id, AuthModel authModel, MergedCalendar mergedCalendar, SharingOptions sharingOptions) {
        this.id = id;
        this.authModel = authModel;
        this.mergedCalendar = mergedCalendar;
    }

    public CalendarId(String id, AuthModel authModel, MergedCalendar mergedCalendar) {
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

    public void setMergedCalendar(MergedCalendar mergedCalendar) {
        this.mergedCalendar = mergedCalendar;
    }

    public void setSharingOptions(SharingOptions sharingOptions) {

        this.sharingOptions = sharingOptions;
        this.sharing_attendants = sharingOptions.getAttendants();
        this.sharing_description = sharingOptions.getDescription();
        this.sharing_location = sharingOptions.getLocation();
        this.sharing_organizer = sharingOptions.getOrganizer();
        this.sharing_title = sharingOptions.getTitle();
    }

    public SharingOptions getSharingOptions() {
        return sharingOptions;
    }
}
