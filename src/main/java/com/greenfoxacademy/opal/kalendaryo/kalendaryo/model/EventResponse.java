package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long eventResponseId;

    //Identifies this as a notification channel used to watch for changes to a resource. Value: the fixed string "api#channel".
    String kind = "api#channel";

    //The channel ID
    String id;

    //It identifies the watched resource, in our case the event, ID of the event
    String resourceId;

    //  example:  "https://www.googleapis.com/calendar/v3/calendars/my_calendar@gmail.com/events"
    String resourceUri;

    public EventResponse() {
    }

    public EventResponse(String kind, String id, String resourceId, String resourceUri) {
        this.kind = kind;
        this.id = id;
        this.resourceId = resourceId;
        this.resourceUri = resourceUri;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getEventResponseId() {
        return eventResponseId;
    }

    public void setEventResponseId(Long eventResponseId) {
        this.eventResponseId = eventResponseId;
    }
}
