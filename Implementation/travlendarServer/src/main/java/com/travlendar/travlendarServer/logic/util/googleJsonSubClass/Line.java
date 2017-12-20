package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

import java.util.List;

public class Line {
    List<Agencies> agencies;
    String color;
    String icon;
    String name;
    String short_name;
    String text_color;
    Vehicle vehicle;

    public List<Agencies> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agencies> agencies) {
        this.agencies = agencies;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
