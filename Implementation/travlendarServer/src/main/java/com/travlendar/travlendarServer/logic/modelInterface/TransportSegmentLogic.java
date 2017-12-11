package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

public interface TransportSegmentLogic {
    void setMeanOfTransport(MeanOfTransportLogic meanOfTransport);
    void setOrigin(Coordinates coordinates);
    void setDestination(Coordinates coordinates);
    boolean isAdiacent(Coordinates coordinates);
    void setDescription(String description);
    void setDuration(int duration);
    void setDistance(int distance);
}
