package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserModel {

    @Id
    private long id;
    @Column(unique = true, nullable = false)
    private String clientToken;
    private String userEmail;
    private String accessToken;

    @OneToMany(mappedBy = "user")
    List<AuthModel> authModelList;

    public UserModel() {
    }

    public UserModel(String clientToken) {
        setClientToken(clientToken);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AuthModel> getAuthModelList() {
        return authModelList;
    }

    public void setAuthModelList(List<AuthModel> authModelList) {
        this.authModelList = authModelList;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setEmailAndToken(String userEmail, String accessToken) {
        setUserEmail(userEmail);
        setAccessToken(accessToken);
    }
}
