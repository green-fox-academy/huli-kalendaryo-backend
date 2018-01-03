package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

public class authResponse {
    
    int userId;
    String clientToken;
    int authId;
    String accessToken;

    public authResponse(int userId, String clientToken, int authId, String accessToken) {
        this.userId = userId;
        this.clientToken = clientToken;
        this.authId = authId;
        this.accessToken = accessToken;
    }

    public int getUserId() {
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

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
