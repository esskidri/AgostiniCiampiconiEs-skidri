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
    private List<String> meanTypes = new ArrayList<>();
    private List<MeanOfTransportLogic> meanOfTransportLogics;

    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic, Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime) {
        List<GoogleResponseMappedObject> googleResponseMappedObjects = new ArrayList<>();
        meanOfTransportLogics = userLogic.getMeanPreferences();

        for(MeanOfTransportLogic meanOfTransportLogic: userLogic.getMeanPreferences()){
            if(!meanTypes.contains(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat())){
                meanTypes.add(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat());
            }
        }

        for(String string: meanTypes){
            googleResponseMappedObjects.add(GoogleAPIHandler.askGoogle(startingLocation.toHttpsFormat(), endingLocation.toHttpsFormat(), string, ((Long) arrivalTime.getTime()).toString()));
        }


        orderTypeByTime(googleResponseMappedObjects, 0);
        reOrderMeanList(0);

        return meanOfTransportLogics;
    }

    private void reOrderMeanList(int index) {
        List<MeanOfTransportLogic> readList = new ArrayList<>();
        readList.addAll(meanOfTransportLogics);
        meanOfTransportLogics.clear();

        for(String string: meanTypes){
            for(MeanOfTransportLogic meanOfTransportLogic: readList)
                if(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat().equals(string)){
                    meanOfTransportLogics.add(meanOfTransportLogic);
                }
        }
    }

    private void orderTypeByTime(List<GoogleResponseMappedObject> googleResponseMappedObjects, int index){
        String best = googleResponseMappedObjects.get(0).getFirstTravelMode();
        int bestIndex = 0;

        for(GoogleResponseMappedObject grmo: googleResponseMappedObjects.subList(index +1, googleResponseMappedObjects.size())){
            if(grmo.getDuration() < googleResponseMappedObjects.get(bestIndex).getDuration()){
                best = grmo.getFirstTravelMode();
                bestIndex = googleResponseMappedObjects.indexOf(grmo);
            }
        }

        if(bestIndex != index) {
            meanTypes.remove(best);
            meanTypes.add(index, best);
        }

        if(index < meanTypes.size() -1)
            orderTypeByTime(googleResponseMappedObjects, index + 1);
    }
}
