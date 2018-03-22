package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

public class SharingOptions {

    Boolean title;
    Boolean organizer;
    Boolean location;
    Boolean attendants;
    Boolean description;

    public SharingOptions() {
        this.title = false;
        this.organizer = false;
        this.location = false;
        this.attendants = false;
        this.description = false;
    }

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Boolean organizer) {
        this.organizer = organizer;
    }

    public Boolean getLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
    }

    public Boolean getAttendants() {
        return attendants;
    }

    public void setAttendants(Boolean attendants) {
        this.attendants = attendants;
    }

    public Boolean getDescription() {
        return description;
    }

    public void setDescription(Boolean description) {
        this.description = description;
    }
}
