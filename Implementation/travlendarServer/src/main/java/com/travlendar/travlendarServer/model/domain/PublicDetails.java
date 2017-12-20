package com.travlendar.travlendarServer.model.domain;

import java.sql.Timestamp;

public class PublicDetails {
    //TODO connect Database, attribute of transport segments, nullable in case the segment is not with public transport
    Float arrivalStopX;
    Float arrivalStopY;
    Float departureStopX;
    Float departureStopY;
    Timestamp arrivalTime;
    Timestamp departureTime;
    Integer numStops;
    String color;
    String lineIconUrl;
    String lineName;
    String short_name;
    String vehicleIconUrl;
    String vehicleName;
    String vehicleType;

}
