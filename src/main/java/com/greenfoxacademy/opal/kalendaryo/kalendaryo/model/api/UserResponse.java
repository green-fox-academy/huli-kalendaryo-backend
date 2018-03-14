package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class UserResponse {

    private long id;
    private String userEmail;
    List<GetAuthResponse> googleAuths;

    public UserResponse() {
    }

    public UserResponse(long id, String userEmail, List<GetAuthResponse> googleAuths) {
        this.id = id;
        this.userEmail = userEmail;
        this.googleAuths = googleAuths;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<GetAuthResponse> getGoogleAuths() {
        return googleAuths;
    }

    public void setGoogleAuths(List<GetAuthResponse> googleAuths) {
        this.googleAuths = googleAuths;
    }
}
