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

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Coordinates))
            return false;

        Coordinates coordinates = (Coordinates) obj;
        return  coordinates.getLat() == this.getLat() && coordinates.getLng() == this.getLng();
    }
}
