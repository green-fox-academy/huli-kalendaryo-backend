package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

public class AuthGetResponse {

    private String email;
    private String accessToken;

    public AuthGetResponse() {
    }

    public AuthGetResponse(String email, String accessToken) {
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
