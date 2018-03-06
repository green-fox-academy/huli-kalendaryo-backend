package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
public class AuthModel {

    @Id
    private String email;
    private String authCode;
    private String displayName;
    private String accessToken;
    private String refreshToken;
    @OneToMany(mappedBy = "authModel")
    List<CalendarId> calendarId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserModel user;

    public AuthModel() {
    }

    public AuthModel(String email, String displayName, UserModel user) {
        this.email = email;
        this.displayName = displayName;
        this.user = user;
    }

    public AuthModel(String email, String authCode, String displayName, UserModel user, String accessToken) {
        this.email = email;
        this.authCode = authCode;
        this.displayName = displayName;
        this.user = user;
        this.accessToken = accessToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<CalendarId> getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(List<CalendarId> calendarId) {
        this.calendarId = calendarId;
    }

    public void setMockAccessToken(char[] chars,  Random randomNumber) {
        accessToken = "";
        for (int i = 0; i < 12 ; i++) {
            int  n = randomNumber.nextInt(62);
            accessToken+= chars[n];
        }
    }

    public void setMockAuthCode(char[] chars, Random randomNumber) {
        authCode = "";
        for (int i = 0; i < 20 ; i++) {
            int  n = randomNumber.nextInt(62);
            authCode+= chars[n];
        }
    }
}
