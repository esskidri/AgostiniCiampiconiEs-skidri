package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.model.MeanType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FastestCore implements CalculatorCore {
    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic, Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime) {
        List<MeanType> meanTypes = new ArrayList<>();
        List<GoogleResponseMappedObject> googleResponseMappedObjects = new ArrayList<>();


        for(MeanOfTransportLogic meanOfTransportLogic: userLogic.getMeanPreferences()){
            if(!meanTypes.contains(meanOfTransportLogic.getTypeOfTransport())){
                meanTypes.add(meanOfTransportLogic.getTypeOfTransport());
            }
        }

        for(MeanType meanType: meanTypes){
            googleResponseMappedObjects.add(GoogleAPIHandler.askGoogle(startingLocation.toHttpsFormat(), endingLocation.toHttpsFormat(), meanType.toHttpsFormat(), ((Long) arrivalTime.getTime()).toString()));
        }

        

    }
}
