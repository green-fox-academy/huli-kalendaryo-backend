package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GoogleAuth {

    @Id
    private String email;
    private String authCode;
    private String displayName;
    private String accessToken;
    private String refreshToken;
    @OneToMany(mappedBy = "googleAuth")
    List<GoogleCalendar> googleCalendars;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    KalUser user;

    public GoogleAuth(String email, String authCode, String displayName, KalUser user, String accessToken) {
        this.email = email;
        this.authCode = authCode;
        this.displayName = displayName;
        this.user = user;
        this.accessToken = accessToken;
    }

    public GoogleAuth() {
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

    public KalUser getUser() {
        return user;
    }

    public void setUser(KalUser user) {
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

    public List<GoogleCalendar> getGoogleCalendars() {
        return googleCalendars;
    }

    public void setGoogleCalendars(List<GoogleCalendar> googleCalendars) {
        this.googleCalendars = googleCalendars;
    }
}
