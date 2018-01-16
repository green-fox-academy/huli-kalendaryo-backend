package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MergedCalendar {

    @Id
    private long userId;
    private String userName;
    private String outputAccount;
    private String outputCalendarId;

    @OneToMany(mappedBy = "mergedCalendar")
    List<CalendarId> calendarId;

    public MergedCalendar(long userId, String userName, String outputAccount, List<CalendarId> calendarId) {
        this.userId = userId;
        this.userName = userName;
        this.outputAccount = outputAccount;
        this.calendarId = calendarId;
    }

    public MergedCalendar() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<CalendarId> getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(List<CalendarId> calendarId) {
        this.calendarId = calendarId;
    }
}
