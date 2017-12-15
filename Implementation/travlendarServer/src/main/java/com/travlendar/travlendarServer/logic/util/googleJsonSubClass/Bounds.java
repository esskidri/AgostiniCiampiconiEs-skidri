package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class Bounds {
    Coordinates northeast;
    Coordinates southwest;

    public Bounds(Coordinates northeast, Coordinates southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Bounds() {
    }

    public Coordinates getNortheast() {
        return northeast;
    }

    public void setNortheast(Coordinates northeast) {
        this.northeast = northeast;
    }

    public Coordinates getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Coordinates southwest) {
        this.southwest = southwest;
    }
}
