package com.greenfoxacademy.opal.kalendaryo.kalendaryo.model;


public class EventResponse {

    //Identifies this as a notification channel used to watch for changes to a resource. Value: the fixed string "api#channel".
    String kind = "api#channel";

    String id;

    String resourceId;

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
}
