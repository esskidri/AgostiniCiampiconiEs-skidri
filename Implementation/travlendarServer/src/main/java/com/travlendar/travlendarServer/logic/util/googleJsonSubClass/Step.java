package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class Step {
    InfoPair distance;
    InfoPair duration;
    Coordinates end_location;
    String html_instructions;
    String maneuver;
    Polyline polyline;
    Coordinates start_location;
    TransitDetails transit_details;
    String travel_mode;


    public Step(InfoPair distance, InfoPair duration, Coordinates end_location, String html_instructions, String maneuver, Polyline polyline, Coordinates start_location, String travel_mode) {
        this.distance = distance;
        this.duration = duration;
        this.end_location = end_location;
        this.html_instructions = html_instructions;
        this.maneuver = maneuver;
        this.polyline = polyline;
        this.start_location = start_location;
        this.travel_mode = travel_mode;
    }

    public Step() {
    }

    /*** Getter and setter for object mapping ***/

    public InfoPair getDistance() {
        return distance;
    }

    public void setDistance(InfoPair distance) {
        this.distance = distance;
    }

    public InfoPair getDuration() {
        return duration;
    }

    public void setDuration(InfoPair duration) {
        this.duration = duration;
    }

    public Coordinates getEnd_location() {
        return end_location;
    }

    public void setEnd_location(Coordinates end_location) {
        this.end_location = end_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Coordinates getStart_location() {
        return start_location;
    }

    public void setStart_location(Coordinates start_location) {
        this.start_location = start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    public TransitDetails getTransit_details() {
        return transit_details;
    }

    public void setTransit_details(TransitDetails transit_details) {
        this.transit_details = transit_details;
    }
}
