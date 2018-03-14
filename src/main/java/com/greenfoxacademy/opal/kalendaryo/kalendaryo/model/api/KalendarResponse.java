package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class KalendarResponse {
    private String outputAccount;
    private String outputCalendarId;
    private List<String> inputCalendarIds;

    public KalendarResponse() {
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

    public List<String> getInputCalendarIds() {
        return inputCalendarIds;
    }

    public void setInputCalendarIds(List<String> inputCalendarIds) {
        this.inputCalendarIds = inputCalendarIds;
    }
}
