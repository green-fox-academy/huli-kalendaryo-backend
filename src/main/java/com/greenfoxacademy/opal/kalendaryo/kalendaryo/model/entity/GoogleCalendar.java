package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.*;

@Entity
public class GoogleCalendar {

    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="auth_model_email")
    GoogleAuth googleAuth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merged_calendar_id")
    Kalendar kalendar;

    public GoogleCalendar(String id, GoogleAuth googleAuth, Kalendar kalendar) {
        this.id = id;
        this.googleAuth = googleAuth;
        this.kalendar = kalendar;
    }

    public GoogleCalendar() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
