package com.greenfoxacademy.kalendaryo.model.api.authresponses;

public class GetAuthResponse {

    private String email;
    private String accessToken;

    public GetAuthResponse() {
    }

    public GetAuthResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
