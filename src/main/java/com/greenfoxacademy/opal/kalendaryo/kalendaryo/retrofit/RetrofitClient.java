package com.greenfoxacademy.opal.kalendaryo.kalendaryo.retrofit;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.GoogleApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    static String BASE_URL_GOOGLE = "https://www.googleapis.com/calendar/v3/";

    public static Retrofit getConnection(String urlType){
        return new Retrofit.Builder()
                .baseUrl(urlType)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build())
                .build();
    }

    public static GoogleApi getGoogleApi() {
        return getConnection(BASE_URL_GOOGLE).create(GoogleApi.class);
    }
}
