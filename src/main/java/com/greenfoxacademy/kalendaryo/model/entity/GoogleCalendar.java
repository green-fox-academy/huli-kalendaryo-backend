package com.greenfoxacademy.kalendaryo.model.entity;

import javax.persistence.*;

@Entity
public class GoogleCalendar {

    @Id
    @GeneratedValue
    private long id;
    private String googleCalendarId;
    @ManyToOne
    @JoinColumn(name="google_auth_id")
    GoogleAuth googleAuth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="kalendar_id")
    Kalendar kalendar;
    String visibility;

    public GoogleCalendar() {
    }

    public GoogleCalendar(String googleCalendarId, GoogleAuth googleAuth, Kalendar kalendar, String visibility) {
        this.googleCalendarId = googleCalendarId;
        this.googleAuth = googleAuth;
        this.kalendar = kalendar;
        this.visibility = visibility;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoogleCalendarId() {
        return googleCalendarId;
    }

    public void setGoogleCalendarId(String googleCalendarId) {
        this.googleCalendarId = googleCalendarId;
    }

    public GoogleAuth getGoogleAuth() {
        return googleAuth;
    }

    public void setGoogleAuth(GoogleAuth googleAuth) {
        this.googleAuth = googleAuth;
    }

    public Kalendar getKalendar() {
        return kalendar;
    }

    public void setKalendar(Kalendar kalendar) {
        this.kalendar = kalendar;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
