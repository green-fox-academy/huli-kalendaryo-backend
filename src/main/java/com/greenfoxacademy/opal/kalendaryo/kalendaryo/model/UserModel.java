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
    @Column(unique = true, nullable = false)
    private String clientToken;
    private String userEmail;

    @OneToMany(mappedBy = "user")
    List<AuthModel> authModelList;

    public UserModel() {
        this.clientToken = getRandomClientToken();
    }

    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
