package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import java.util.List;

public class UserResponse {

    private long id;
    private String userEmail;
    List<GetAuthResponse> authModels;

    public UserResponse() {
    }

    public UserResponse(long id, String userEmail, List<GetAuthResponse> authModels) {
        this.id = id;
        this.userEmail = userEmail;
        this.authModels = authModels;
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

    public List<GetAuthResponse> getAuthModels() {
        return authModels;
    }

    public void setAuthModels(List<GetAuthResponse> authModels) {
        this.authModels = authModels;
    }
}
