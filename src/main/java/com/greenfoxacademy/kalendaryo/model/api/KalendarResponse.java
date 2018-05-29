package com.greenfoxacademy.kalendaryo.model.api;

import java.util.List;

public class KalendarResponse {
    private String outputGoogleAuthId;
    private String outputCalendarId;
    private List<String> inputGoogleCalendars;
    private long id;

    public KalendarResponse() {
    }

    public KalendarResponse(String outputGoogleAuthId, String outputCalendarId, List<String> inputGoogleCalendars, long id) {
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
        this.inputGoogleCalendars = inputGoogleCalendars;
        this.id = id;
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

    public List<String> getInputGoogleCalendars() {
        return inputGoogleCalendars;
    }

    public void setInputGoogleCalendars(List<String> inputGoogleCalendars) {
        this.inputGoogleCalendars = inputGoogleCalendars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
