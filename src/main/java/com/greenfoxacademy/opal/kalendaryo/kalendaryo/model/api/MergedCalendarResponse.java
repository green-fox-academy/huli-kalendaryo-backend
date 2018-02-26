package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class MergedCalendarResponse {
    private String outputAccountId;
    private String outputCalendarId;
    private List<String> inputCalendarIds;

    public MergedCalendarResponse() {
    }

    public String getOutputAccountId() {
        return outputAccountId;
    }

    public void setOutputAccountId(String outputAccountId) {
        this.outputAccountId = outputAccountId;
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
