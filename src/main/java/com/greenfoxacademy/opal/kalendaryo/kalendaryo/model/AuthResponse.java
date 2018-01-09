package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

public class AuthResponse {
    private long userId;
    private String clientToken;
    private String userEmail;
    private String accessToken;

    public AuthResponse(long userId, String clientToken, String userEmail, String accessToken) {
        this.userId = userId;
        this.clientToken = clientToken;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
