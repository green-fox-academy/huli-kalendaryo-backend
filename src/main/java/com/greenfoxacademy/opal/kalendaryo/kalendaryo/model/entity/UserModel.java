package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;

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

//    @OneToMany(mappedBy = "user")
//    List<MergedCalendar> mergedCalendarList;

    public UserModel() {
    }

    public UserModel(String clientToken, String userEmail,
        List<AuthModel> authModelList,
        List<MergedCalendar> mergedCalendars) {
        this.clientToken = clientToken;
        this.userEmail = userEmail;
        this.authModelList = authModelList;
        //this.mergedCalendarList = mergedCalendars;
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

    public List<AuthModel> getAuthModelList() {
        return authModelList;
    }

    public void setAuthModelList(
        List<AuthModel> authModelList) {
        this.authModelList = authModelList;
    }

//    public List<MergedCalendar> getMergedCalendarList() {
//        return mergedCalendarList;
//    }
//
//    public void setMergedCalendarList(
//        List<MergedCalendar> mergedCalendarList) {
//        this.mergedCalendarList = mergedCalendarList;
//    }
}
