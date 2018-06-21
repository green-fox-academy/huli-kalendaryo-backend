package com.greenfoxacademy.kalendaryo.model.entity;


import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Kalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user")
    private KalUser user;
    private String outputGoogleAuthId;
    private String name;
    private String googleCalendarId;
    private Date lastSync;

    @OneToMany(mappedBy = "kalendar")
    List<GoogleCalendar> googleCalendars;

    public Kalendar() {
      lastSync = new Date();
    }

    public Kalendar(String outputGoogleAuthId, String name) {
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.name = name;
    }

    public Kalendar(KalUser user, String outputGoogleAuthId, String name) {
        this.user = user;
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.name = name;
    }

    public Kalendar(long id, KalUser user, String outputGoogleAuthId, String name, List<GoogleCalendar> googleCalendars) {
        this.id = id;
        this.user = user;
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.name = name;
        this.googleCalendars = googleCalendars;
        lastSync = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KalUser getUser() {
        return user;
    }

    public void setUser(KalUser user) {
        this.user = user;
    }

    public String getOutputGoogleAuthId() {
        return outputGoogleAuthId;
    }

    public void setOutputGoogleAuthId(String outputGoogleAuthId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoogleCalendar> getGoogleCalendars() {
        return googleCalendars;
    }

    public void setGoogleCalendars(List<GoogleCalendar> googleCalendars) {
        this.googleCalendars = googleCalendars;
    }

    public String getGoogleCalendarId() {
        return googleCalendarId;
    }

    public void setGoogleCalendarId(String googleCalendarId) {
        this.googleCalendarId = googleCalendarId;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    public List<GoogleCalendar> getGoogleCalendarList(String[] arrayOfStrings) {
        List<GoogleCalendar> googleCalendars = new ArrayList<>();
        for (int i = 0; i < arrayOfStrings.length; i++) {
            GoogleCalendar googleCalendar = new GoogleCalendar();
            googleCalendar.setId(arrayOfStrings[i]);
            googleCalendar.setGoogleAuth(null);
            googleCalendar.setKalendar(null);
            googleCalendars.add(googleCalendar);
        }
        return googleCalendars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kalendar kalendar = (Kalendar) o;

        return this.getName().equals(kalendar.getName());
    }
}
