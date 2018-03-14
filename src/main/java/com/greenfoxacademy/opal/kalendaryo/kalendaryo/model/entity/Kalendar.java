package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;


import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
public class Kalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user")
    private KalUser user;
    private String outputAccount;
    private String outputCalendarId;
  
    @OneToMany(mappedBy = "kalendar")
    List<GoogleCalendar> googleCalendars;

    public Kalendar() {
    }

    public Kalendar(KalUser user, String outputAccount, String outputCalendarId) {
        this.user = user;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
    }

    public Kalendar(long id, KalUser user, String outputAccount,
                    String outputCalendarId, List<GoogleCalendar> googleCalendars) {
        this.id = id;
        this.user = user;
        this.outputAccount = outputAccount;
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

    public String getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(String outputAccount) {
        this.outputAccount = outputAccount;
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
            googleCalendar.setAuthModel(null);
            googleCalendar.setKalendar(null);
            googleCalendars.add(googleCalendar);
        }
        return googleCalendars;
    }
}
