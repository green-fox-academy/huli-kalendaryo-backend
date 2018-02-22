package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;

import java.util.List;

public class UserResponse {

    private long id;
    private String userEmail;
    List<AuthGetResponse> authModels;

    public UserResponse() {
    }

    public UserResponse(long id, String userEmail, List<AuthGetResponse> authModels) {
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

    public List<AuthGetResponse> getAuthModels() {
        return authModels;
    }

    public void setAuthModels(List<AuthGetResponse> authModels) {
        this.authModels = authModels;
    }
}
