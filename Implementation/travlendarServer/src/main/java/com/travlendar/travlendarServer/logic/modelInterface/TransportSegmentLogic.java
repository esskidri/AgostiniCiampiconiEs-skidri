package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

import java.sql.Time;
import java.sql.Timestamp;

public interface TransportSegmentLogic {
    void setMeanOfTransport(MeanOfTransportLogic meanOfTransport);
    void setOrigin(Coordinates coordinates);
    void setDestination(Coordinates coordinates);
    Coordinates getOrigin();
    Coordinates getDestination();
    boolean isAdiacent(Coordinates coordinates);
    void setDescription(String description);
    void setDuration(long duration);
    void setDistance(long distance);
    void setOrder(int numOrder);
    void setDepartureTime(Timestamp departingTime);
    void setArrivalTime(Timestamp arrivalTime);
    long getDuration();
    long getDistance();


    default void setAll(Coordinates origin,
                        Coordinates destination,
                        long distance,
                        long duration,
                        Timestamp departureTime,
                        Timestamp arrivalTime, MeanOfTransportLogic meanOfTransportLogic){
        setOrigin(origin);
        setDestination(destination);
        setDistance(distance);
        setDuration(duration);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setMeanOfTransport(meanOfTransportLogic);
    }
}
