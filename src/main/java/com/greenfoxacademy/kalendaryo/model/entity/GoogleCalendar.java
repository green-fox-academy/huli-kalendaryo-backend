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

    public GoogleCalendar() {
    }

    public GoogleCalendar(long id, String googleCalendarId, GoogleAuth googleAuth, Kalendar
        kalendar) {
        this.id = id;
        this.googleCalendarId = googleCalendarId;
        this.googleAuth = googleAuth;
        this.kalendar = kalendar;
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
}
