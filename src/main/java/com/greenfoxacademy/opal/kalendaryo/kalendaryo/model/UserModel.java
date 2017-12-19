package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.*;
import java.util.List;

public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String clientToken;

    @OneToMany
    List<AuthModel> authModelList;

    public UserModel() {
    }

    public UserModel(String clientToken) {
        this.clientToken = clientToken;
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
}
