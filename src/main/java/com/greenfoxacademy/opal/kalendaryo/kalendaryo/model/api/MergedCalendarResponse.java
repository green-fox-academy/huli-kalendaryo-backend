package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class MergedCalendarResponse {
    private String outputAccountId;
    private String outputCalendarId;
    private List<String> inputCalendarId;

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

    public List<String> getInputCalendarId() {
        return inputCalendarId;
    }

    public void setInputCalendarId(List<String> inputCalendarId) {
        this.inputCalendarId = inputCalendarId;
    }
}
