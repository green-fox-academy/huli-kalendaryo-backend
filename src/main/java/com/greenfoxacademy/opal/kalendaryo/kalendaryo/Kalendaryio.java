package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

public class Kalendaryio {

    String id;
    String name;
    String kind;

    public Kalendaryio(String id, String name, String kind) {
        this.id = id;
        this.name = name;
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
