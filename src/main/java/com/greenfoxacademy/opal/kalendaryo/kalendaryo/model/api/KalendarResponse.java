package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class KalendarResponse {
    private String outputGoogleAuthId;
    private String outputCalendarId;
    private List<String> inputGoogleCalendars;

    public KalendarResponse() {
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
}
