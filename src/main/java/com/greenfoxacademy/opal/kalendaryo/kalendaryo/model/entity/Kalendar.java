package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;


import java.util.ArrayList;
import javax.persistence.*;
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
    private String outputCalendarId;

    @OneToMany(mappedBy = "kalendar")
    List<GoogleCalendar> googleCalendars;

    public Kalendar() {
    }

    public Kalendar(String outputGoogleAuthId, String outputCalendarId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
    }

    public Kalendar(KalUser user, String outputGoogleAuthId, String outputCalendarId) {
        this.user = user;
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
    }

    public Kalendar(long id, KalUser user, String outputGoogleAuthId, String outputCalendarId, List<GoogleCalendar> googleCalendars) {
        this.id = id;
        this.user = user;
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
        this.googleCalendars = googleCalendars;
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

    public String getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(String outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }

    public List<GoogleCalendar> getGoogleCalendars() {
        return googleCalendars;
    }

    public void setGoogleCalendars(List<GoogleCalendar> googleCalendars) {
        this.googleCalendars = googleCalendars;
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

        return this.getOutputCalendarId().equals(kalendar.getOutputCalendarId());
    }
}
