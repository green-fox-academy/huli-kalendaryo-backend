package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;


import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MergedCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String outputAccount;
    private String outputCalendarId;
    @OneToMany(mappedBy = "mergedCalendar")
    List<CalendarId> CalendarIds;

    public MergedCalendar(long id, String userName, String outputAccount, String outputCalendarId,
        List<CalendarId> CalendarIds) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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

    public void setCalendarIds(
        List<CalendarId> calendarIds) {
        this.CalendarIds = CalendarIds;
    }


    public List<CalendarId> getCalendarIds(String[] arrayOfStrings) {
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
