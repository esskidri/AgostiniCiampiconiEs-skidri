package com.travlendar.travlendarServer.logic.util;

import java.util.List;

public class GoogleResponseMappedObject {
    private List<GeocodedWaypoint> geocodedWaypoints;
    private List<Route> routes;


    private class GeocodedWaypoint {
        private String geocoder_status;
        private boolean partial_match;
        private String place_id;
        private List<String> types;
    }

    private class Route {
    }
}
