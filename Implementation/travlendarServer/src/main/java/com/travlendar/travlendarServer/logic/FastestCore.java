package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;
import com.travlendar.travlendarServer.logic.util.TimeRequest;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FastestCore implements CalculatorCore {
    private List<String> meanTypes = new ArrayList<>();
    private List<MeanOfTransportLogic> meanOfTransportLogics = new ArrayList<>();

    /**
     *
     * Implementation of the main core function for the Fast Policy
     * The Method compact the mean of the user in the (maximum) four travel mode handled by Google
     * then it call Google API for each travel mode
     * The list of google response is re-ordered by time duration
     * Then the mean of transport are ordered by the travel mode list obtained by Google
     *
     * @param meansOfTransport
     * @param startingLocation Coordinates of the starting location
     * @param endingLocation Coordinates of the ending location
     * @param startingTime TimeStamp of the starting time
     * @param arrivalTime TimeStamp of the arrival time
     * @return
     */
    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(List<MeanOfTransportLogic> meansOfTransport, Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime, TimeRequest timeRequest) {
        List<GoogleResponseMappedObject> googleResponseMappedObjects = new ArrayList<>();
        meanOfTransportLogics.addAll(meansOfTransport);

        for(MeanOfTransportLogic meanOfTransportLogic: meansOfTransport){
            if(!meanTypes.contains(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat())){
                meanTypes.add(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat());
            }
        }

        for(String string: meanTypes){
            //TODO get arrival or departure time
            googleResponseMappedObjects.add(GoogleAPIHandler.askGoogle(startingLocation, endingLocation, string, timeRequest.toHttpsFormat() ,arrivalTime));
        }


        orderTypeByTime(googleResponseMappedObjects, 0);
        reOrderMeanList(0);

        return meanOfTransportLogics;
    }

    /**
     * @param index for the recursive call
     */
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

    /**
     * @param googleResponseMappedObjects List of Mapped Google response Object for query the time duration of the trip
     * @param index for the recursive call
     */
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
