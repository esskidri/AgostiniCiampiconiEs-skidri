package com.travlendar.travlendarServer.logic.util.googleJsonSubClass;

public class TransitDetails {
    Stop arrival_stop;
    InfoPair arrival_time;
    Stop departure_stop;
    InfoPair departure_time;
    String headsign;
    Line line;
    int num_stops;

    public Stop getArrival_stop() {
        return arrival_stop;
    }

    public void setArrival_stop(Stop arrival_stop) {
        this.arrival_stop = arrival_stop;
    }

    public InfoPair getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(InfoPair arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Stop getDeparture_stop() {
        return departure_stop;
    }

    public void setDeparture_stop(Stop departure_stop) {
        this.departure_stop = departure_stop;
    }

    public InfoPair getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(InfoPair departure_time) {
        this.departure_time = departure_time;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public int getNum_stops() {
        return num_stops;
    }

    public void setNum_stops(int num_stops) {
        this.num_stops = num_stops;
    }
}
