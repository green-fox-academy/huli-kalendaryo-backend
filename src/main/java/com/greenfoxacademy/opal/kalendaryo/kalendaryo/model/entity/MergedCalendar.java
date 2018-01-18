package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    List<CalendarId> calendarId;

/*
    @ManyToOne
    UserModel user;
*/

    public MergedCalendar(long id, String userName, String outputAccount,
        String outputCalendarId,
        List<CalendarId> calendarId,
        UserModel userModel) {
        this.id = id;
        this.userName = userName;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
        this.calendarId = calendarId;
        //this.user = userModel;
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

    public List<CalendarId> getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(
        List<CalendarId> calendarId) {
        this.calendarId = calendarId;
    }

//    public UserModel getUser() {
//        return user;
//    }
//
//    public void setUser(UserModel user) {
//        this.user = user;
//    }
}
