package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class KalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String clientToken;
    private String userEmail;
    @OneToMany(mappedBy = "user")
    List<Kalendar> kalendarList;

    @OneToMany(mappedBy = "user")
    List<GoogleAuth> googleAuthList;

    public KalUser() {
    }

    public KalUser(String clientToken, String userEmail) {
        this.clientToken = clientToken;
        this.userEmail = userEmail;
    }

    public KalUser(String clientToken, String userEmail, List<GoogleAuth> googleAuthList) {
        this.clientToken = clientToken;
        this.userEmail = userEmail;
        this.googleAuthList = googleAuthList;
    }

    public KalUser(String clientToken) {
        setClientToken(clientToken);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Kalendar> getKalendarList() {
        return kalendarList;
    }

    public void setKalendarList(List<Kalendar> kalendarList) {
        this.kalendarList = kalendarList;
    }

    public void addKalendarToUser(Kalendar kalendar) {
        kalendar.setUser(this);
        kalendarList.add(kalendar);
    }

    public List<GoogleAuth> getGoogleAuthList() {
        return googleAuthList;
    }

    public void setGoogleAuthList(List<GoogleAuth> googleAuthList) {
        this.googleAuthList = googleAuthList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KalUser kalUser = (KalUser) o;

        return this.getUserEmail().equals(kalUser.getUserEmail());
    }
}
