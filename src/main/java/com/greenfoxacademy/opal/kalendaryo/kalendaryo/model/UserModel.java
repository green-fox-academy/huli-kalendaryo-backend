package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

import com.google.api.client.util.Base64;
import java.security.SecureRandom;
import javax.persistence.*;
import java.util.List;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String clientToken;
    private byte[] random;
    private SecureRandom secureRandom;


    @OneToMany
    List<AuthModel> authModelList;

    public UserModel() {
    }

    public String getRandomClientToken() {
        secureRandom = new SecureRandom();
        random = new byte[256];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);

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
