package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.GeocodedWaypoint;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Route;

import java.io.Serializable;
import java.util.List;

/**
 * This class contain all the attributes needed to grant an useful mapping with the google response
 * ObjectMapper of the jackson databind library contain the method to grant a correct mapping
 * This class will also implement useful method to extract important information for out algorithm
 */
public class GoogleResponseMappedObject implements Serializable {
    List<GeocodedWaypoint> geocoded_waypoints;
    List<Route> routes;


    /*** Getter and setter for object mapping ***/

    public List<GeocodedWaypoint> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(List<GeocodedWaypoint> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
