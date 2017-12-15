package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

import java.util.List;

public class Leg {
    InfoPair distance;
    InfoPair duration;
    String end_address;
    Coordinates end_location;
    String start_address;
    Coordinates start_location;
    List<Step> steps;
    List<String> warnings;
    List<Integer> waypoint_order;

    public Leg(InfoPair distance, InfoPair duration, String end_address, Coordinates end_location, String start_address, Coordinates start_location, List<Step> steps, List<String> warnings, List<Integer> waypoint_order) {
        this.distance = distance;
        this.duration = duration;
        this.end_address = end_address;
        this.end_location = end_location;
        this.start_address = start_address;
        this.start_location = start_location;
        this.steps = steps;
        this.warnings = warnings;
        this.waypoint_order = waypoint_order;
    }

    public Leg() {
    }

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

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public Coordinates getEnd_location() {
        return end_location;
    }

    public void setEnd_location(Coordinates end_location) {
        this.end_location = end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public Coordinates getStart_location() {
        return start_location;
    }

    public void setStart_location(Coordinates start_location) {
        this.start_location = start_location;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<Integer> getWaypoint_order() {
        return waypoint_order;
    }

    public void setWaypoint_order(List<Integer> waypoint_order) {
        this.waypoint_order = waypoint_order;
    }
}
