package com.greenfoxacademy.opal.kalendaryo.kalendaryo.httpcon;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Component
public class RetrofitService {

    AuthInterceptor authInterceptor = new AuthInterceptor();

    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(authInterceptor).build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();

    HttpConService service = retrofit.create(HttpConService.class);

    public HttpConService getService() {
        return service;
    }
}
