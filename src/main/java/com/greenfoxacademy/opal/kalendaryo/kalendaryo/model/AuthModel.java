package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AuthModel {

    @Id
    private String email;
    private String accessToken;

    @ManyToOne
    UserModel user;

    public AuthModel(String email, String accessToken, UserModel user) {
        this.email = email;
        this.accessToken = accessToken;
        this.user = user;
    }

    public AuthModel() {
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
