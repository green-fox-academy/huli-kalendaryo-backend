package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long eventResponseId;

    //The channel ID
    String channelId;

    //It identifies the watched resource, in our case the event, ID of the event
    String resourceId;

    //  example:  "https://www.googleapis.com/calendar/v3/calendars/my_calendar@gmail.com/events"
    String resourceUri;

    String resourceState;

    int messageNumber;

    @JsonInclude(Include.NON_NULL)
    String channelExpiration;

    @JsonInclude(Include.NON_NULL)
    String channelToken;

    public EventResponse() {
    }

    public EventResponse(String channelId, String resourceId, String resourceUri,
        String resourceState, int messageNumber, String channelExpiration,
        String channelToken) {
        this.channelId = channelId;
        this.resourceId = resourceId;
        this.resourceUri = resourceUri;
        this.resourceState = resourceState;
        this.messageNumber = messageNumber;
        this.channelExpiration = channelExpiration;
        this.channelToken = channelToken;
    }

    public Long getEventResponseId() {
        return eventResponseId;
    }

    public void setEventResponseId(Long eventResponseId) {
        this.eventResponseId = eventResponseId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getResourceState() {
        return resourceState;
    }

    public void setResourceState(String resourceState) {
        this.resourceState = resourceState;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getChannelExpiration() {
        return channelExpiration;
    }

    public void setChannelExpiration(String channelExpiration) {
        this.channelExpiration = channelExpiration;
    }

    public String getChannelToken() {
        return channelToken;
    }

    public void setChannelToken(String channelToken) {
        this.channelToken = channelToken;
    }
}
