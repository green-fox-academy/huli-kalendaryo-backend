package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

@Entity
public class GoogleCalendarUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String channelId;
    String resourceId;
    // example:  "https://www.googleapis.com/calendar/v3/calendars/my_calendar@gmail.com/events"
    String resourceUri;
    String resourceState;
    String messageNumber;
    @JsonInclude(Include.NON_NULL)
    String channelExpiration;
    @JsonInclude(Include.NON_NULL)
    String channelToken;

    public GoogleCalendarUpdate() {
    }

    public GoogleCalendarUpdate(HttpServletRequest request) {
        this.channelId = request.getHeader("X-Goog-Channel-ID");
        this.resourceId = request.getHeader("X-Goog-Resource-ID");
        this.resourceUri = request.getHeader("X-Goog-Resource-URI");
        this.resourceState = request.getHeader("X-Goog-Resource-State");
        this.messageNumber = request.getHeader("X-Goog-Message-Number");
        this.channelExpiration = request.getHeader("X-Goog-Channel-Expiration");
        this.channelToken = request.getHeader("X-Goog-Channel-Token");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long eventResponseId) {
        this.id = eventResponseId;
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

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
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

    public boolean validate() {
        return getChannelId() == null || getResourceId() == null || getResourceState() == null || getMessageNumber() == null || getResourceUri() == null;
    }

    public String getMissingFields() {
        String missing = "";

        if (getChannelId() == null) {
            missing = "channel id";
        } else if (getResourceId() == null) {
            missing = "resource id";
        } else if (getResourceState() == null) {
            missing = "resource state";
        } else if (getMessageNumber() == null) {
            missing = "message number";
        } else if (getResourceUri() == null) {
            missing = "resource uri";
        }

        return missing;
    }
}
