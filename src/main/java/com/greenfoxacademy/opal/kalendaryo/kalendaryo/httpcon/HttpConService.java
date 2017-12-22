package com.greenfoxacademy.opal.kalendaryo.kalendaryo.httpcon;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import org.springframework.http.HttpStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpConService {

    @POST("/auth")
    Call<HttpStatus> getAuthToken(@Body AuthModel auth);
}
