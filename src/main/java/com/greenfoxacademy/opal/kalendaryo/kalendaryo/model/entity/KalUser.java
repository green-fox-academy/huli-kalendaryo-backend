package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

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
    List<AuthModel> authModelList;

    public KalUser() {
    }

    public KalUser(String clientToken, String userEmail) {
        this.clientToken = clientToken;
        this.userEmail = userEmail;
    }

    public KalUser(String clientToken, String userEmail, List<AuthModel> authModelList) {
        this.clientToken = clientToken;
        this.userEmail = userEmail;
        this.authModelList = authModelList;
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

    public List<AuthModel> getAuthModelList() {
        return authModelList;
    }

    public void setAuthModelList(List<AuthModel> authModelList) {
        this.authModelList = authModelList;
    }
}
