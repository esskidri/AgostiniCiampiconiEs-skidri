package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

import java.util.List;

public class Route {
    Bounds bounds;
    String copyrights;
    List<Leg> legs;
    Polyline overview_polyline;
    String summary;

    public Route(Bounds bounds, String copyrights, List<Leg> legs, Polyline overview_polyline, String summary) {
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.legs = legs;
        this.overview_polyline = overview_polyline;
        this.summary = summary;
    }

    public Route() {
    }

    /*** Getter and setter for object mapping ***/

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Polyline getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(Polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
