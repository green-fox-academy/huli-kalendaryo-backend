package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

public class EventResponse {

    //@Id
    Long eventResponseId;

    String channelId;

    String resourceId;

    String resourceUri;

    String resourceState;

    Integer messageNumber;

    @JsonInclude(Include.NON_NULL)
    String channelExpiration;

    @JsonInclude(Include.NON_NULL)
    String channelToken;

    public EventResponse() {
    }

    public EventResponse(HttpServletRequest request) {
        this.channelId = request.getHeader("X-Goog-Channel-ID");
        this.resourceId = request.getHeader("X-Goog-Resource-ID");
        this.resourceUri = request.getHeader("X-Goog-Resource-URI");
        this.resourceState = request.getHeader("X-Goog-Resource-State");
        this.messageNumber = Integer.parseInt(request.getHeader("X-Goog-Message-Number"));
        this.channelExpiration = request.getHeader("X-Goog-Channel-Expiration");
        this.channelToken = request.getHeader("X-Goog-Channel-Token");
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

    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(Integer messageNumber) {
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

    public HttpStatus validate() {

        if (getChannelId() == null ||
                getResourceId() == null ||
                getResourceState() == null ||
                getMessageNumber() == null ||
                getResourceUri() == null) {
            return HttpStatus.NOT_ACCEPTABLE;
        } else {
            return HttpStatus.OK;
        }
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
