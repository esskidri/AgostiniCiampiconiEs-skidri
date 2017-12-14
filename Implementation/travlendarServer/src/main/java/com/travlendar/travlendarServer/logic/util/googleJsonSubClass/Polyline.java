package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class Polyline {
    String points;

    public Polyline(String points) {
        this.points = points;
    }

    public Polyline() {
    }

    /*** Getter and setter for object mapping ***/

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
