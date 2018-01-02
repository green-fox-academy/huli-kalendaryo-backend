package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.*;

@Entity
public class AuthModel {

    @Id
    private String email;

    private String authCode;

    private String displayName;

    @ManyToOne(cascade = CascadeType.ALL)
    UserModel user;

    public AuthModel(String email, String authCode, UserModel user) {
        this.email = email;
        this.authCode = authCode;
        this.user = user;
    }

    public AuthModel() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
