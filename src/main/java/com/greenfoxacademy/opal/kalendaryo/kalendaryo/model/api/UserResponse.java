package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;

import java.util.List;

public class UserResponse {

    private long id;
    private String userEmail;
    List<AuthModel> authModels;

    public UserResponse() {
    }

    public UserResponse(long id, String userEmail, List<AuthModel> authModels) {
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

    public List<AuthModel> getAuthModels() {
        return authModels;
    }

    public void setAuthModels(List<AuthModel> authModels) {
        this.authModels = authModels;
    }
}
