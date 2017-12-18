package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class Stop {
    Coordinates location;
    String name;

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
