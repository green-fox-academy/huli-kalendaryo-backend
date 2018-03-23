package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class KalendarListResponse {

    private List<KalendarResponse> kalendars;

    public KalendarListResponse() {
  }

    public List<KalendarResponse> getKalendars() {
    return kalendars;
  }

    public void setKalendars(List<KalendarResponse> kalendars) {
        this.kalendars = kalendars;
  }
}
