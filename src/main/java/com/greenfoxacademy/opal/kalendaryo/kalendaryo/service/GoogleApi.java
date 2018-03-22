package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.google.api.services.calendar.model.Calendar;

public interface GoogleApi {
    @Headers("Accept: application/json")
    @POST("calendars")
    Call<Calendar> postGoogleCalendar(@Header("Authorization") String authorization,Calendar calendar);
}
