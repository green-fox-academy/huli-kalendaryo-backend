package com.greenfoxacademy.kalendaryo.model.entity;

import javax.persistence.*;

@Entity
public class GoogleCalendar {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name="google_auth_id")
    GoogleAuth googleAuth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="kalendar_id")
    Kalendar kalendar;
    String visibility;

    public GoogleCalendar() {
    }

    public GoogleCalendar(String id, GoogleAuth googleAuth, Kalendar kalendar, String visibility) {
        this.id = id;
        this.googleAuth = googleAuth;
        this.kalendar = kalendar;
        this.visibility = visibility;
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
