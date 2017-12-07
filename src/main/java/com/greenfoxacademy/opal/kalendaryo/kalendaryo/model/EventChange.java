package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;

public class EventChange {

    // The channel ID
    String channelId;

    // The type of delivery mechanism in the channel, web_hook in our case
    String type;

    // Where the notifications will go, the receiving url
    String address;

    public EventChange() {
    }

    public EventChange(String channelId, String type, String address) {
        this.channelId = channelId;
        this.type = type;
        this.address = address;
    }

    public String getId() {
        return channelId;
    }

    public void setId(String channelId) {
        this.channelId = channelId;
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
