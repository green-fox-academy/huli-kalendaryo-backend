package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

public class EventChange {

    // The id that identifies the channel
    String id;

    // The type of delivery mechanism
    String type;

    // Where the notifications will go
    String address;

    public EventChange() {
    }

    public EventChange(String id, String type, String address) {
        this.id = id;
        this.type = type;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
