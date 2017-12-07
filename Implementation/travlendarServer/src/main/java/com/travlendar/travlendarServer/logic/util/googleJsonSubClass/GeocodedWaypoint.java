package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

import java.io.Serializable;
import java.util.List;

public class GeocodedWaypoint implements Serializable {
    String geocoder_status;
    Boolean partial_match;
    String place_id;
    List<String> types;

    public String getGeocoder_status() {
        return geocoder_status;
    }

    public void setGeocoder_status(String geocoder_status) {
        this.geocoder_status = geocoder_status;
    }

    public Boolean getPartial_match() {
        return partial_match;
    }

    public void setPartial_match(Boolean partial_match) {
        this.partial_match = partial_match;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
