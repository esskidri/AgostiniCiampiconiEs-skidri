package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class Coordinates {
    Float lat;
    Float lng;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String toHttpsFormat() {
        return lat.toString() + "," +lng.toString();
    }
}
