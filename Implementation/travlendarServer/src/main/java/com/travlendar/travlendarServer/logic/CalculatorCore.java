package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.TimeRequest;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

import java.sql.Timestamp;
import java.util.List;

public interface CalculatorCore {
    List<MeanOfTransportLogic> getMeanOfTransports(List<MeanOfTransportLogic> meansOfTransport, Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime, TimeRequest timeRequest);
}
