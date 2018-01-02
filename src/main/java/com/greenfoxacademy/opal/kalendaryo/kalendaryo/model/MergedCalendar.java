package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import java.util.List;

public class MergedCalendar {

    private long userId;
    private String userName;
    private List<String> inputCalendarId;
    private String outputAccount;
    private List<String> outputCalendarId;

    public MergedCalendar(long userId, String userName, List<String> inputCalendarId, String outputAccount, List<String> outputCalendarId) {
        this.userId = userId;
        this.userName = userName;
        this.inputCalendarId = inputCalendarId;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
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

    public List<String> getInputCalendarId() {
        return inputCalendarId;
    }

    public void setInputCalendarId(List<String> inputCalendarId) {
        this.inputCalendarId = inputCalendarId;
    }

    public String getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(String outputAccount) {
        this.outputAccount = outputAccount;
    }

    public List<String> getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(List<String> outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }
}
