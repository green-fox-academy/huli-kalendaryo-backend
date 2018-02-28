package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;


import org.apache.catalina.User;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
public class MergedCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private UserModel userName;
    private String outputAccount;
    private String outputCalendarId;
  
    @OneToMany(mappedBy = "mergedCalendar")
    List<CalendarId> CalendarIds;

    public MergedCalendar(long id, UserModel userName, String outputAccount,
        String outputCalendarId, List<CalendarId> CalendarIds) {
        this.id = id;
        this.userName = userName;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
        this.CalendarIds = CalendarIds;
    }

    public MergedCalendar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getUserName() {
        return userName;
    }

    public void setUserName(UserModel userName) {
        this.userName = userName;
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

    public List<CalendarId> getCalendarIds() {
        return CalendarIds;
    }

    public void setCalendarIds(List<CalendarId> CalendarIds) {
        this.CalendarIds = CalendarIds;
    }

    public List<CalendarId> getCalendarsIds(String[] arrayOfStrings) {
        List<CalendarId> calendarIds = new ArrayList<>();
        for (int i = 0; i < arrayOfStrings.length; i++) {
            CalendarId calendarId = new CalendarId();
            calendarId.setId(arrayOfStrings[i]);
            calendarId.setAuthModel(null);
            calendarId.setMergedCalendar(null);
            calendarIds.add(calendarId);
        }
        return calendarIds;
    }
}
