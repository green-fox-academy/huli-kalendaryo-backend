package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String accessToken;

    @OneToMany
    List<AuthModel> authModelList;

    public UserModel(String clientToken) {
        this.accessToken = clientToken;
    }

    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientToken() {
        return accessToken;
    }

    public void setClientToken(String clientToken) {
        this.accessToken = clientToken;
    }

    public List<AuthModel> getAuthModelList() {
        return authModelList;
    }

    public void setAuthModelList(List<AuthModel> authModelList) {
        this.authModelList = authModelList;
    }
}
