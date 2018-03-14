package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.*;

@Entity
public class GoogleCalendar {

    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="auth_model_email")
    AuthModel authModel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="merged_calendar_id")
    Kalendar kalendar;

    public GoogleCalendar(String id, AuthModel authModel, Kalendar kalendar) {
        this.id = id;
        this.authModel = authModel;
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

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public Kalendar getKalendar() {
        return kalendar;
    }

    public void setKalendar(Kalendar kalendar) {
        this.kalendar = kalendar;
    }
}
