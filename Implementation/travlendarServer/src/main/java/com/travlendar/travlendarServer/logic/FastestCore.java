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
     * @param departureTime TimeStamp of the starting time
     * @param arrivalTime TimeStamp of the arrival time
     * @return
     */
    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(List<MeanOfTransportLogic> meansOfTransport, Coordinates startingLocation, Coordinates endingLocation, Timestamp departureTime, Timestamp arrivalTime, TimeRequest timeRequest) {
        List<GoogleResponseMappedObject> googleResponseMappedObjects = new ArrayList<>();
        meanOfTransportLogics.addAll(meansOfTransport);
        try {
            for (MeanOfTransportLogic meanOfTransportLogic : meansOfTransport) {
                if (!meanTypes.contains(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat())) {
                    meanTypes.add(meanOfTransportLogic.getTypeOfTransport().toHttpsFormat());
                }
            }

            GoogleResponseMappedObject grmo;

            List<String> readList = new ArrayList<>();
            readList.addAll(meanTypes);

            for (String string : readList) {
                if (timeRequest == TimeRequest.ARRIVAL)
                    grmo = GoogleAPIHandler.askGoogle(startingLocation, endingLocation, string, timeRequest.toHttpsFormat(), arrivalTime);
                else
                    grmo = GoogleAPIHandler.askGoogle(startingLocation, endingLocation, string, timeRequest.toHttpsFormat(), departureTime);

                if(grmo.getRoutes() == null ||
                        grmo.getLeg() == null ||
                        grmo.getSteps() == null ||
                        grmo.getSteps().isEmpty()) {
                    meanTypes.remove(string);
                }
                else
                    googleResponseMappedObjects.add(grmo);
            }

            //clearInvalidObject(googleResponseMappedObjects);


            if (!googleResponseMappedObjects.isEmpty())
                orderTypeByTime(googleResponseMappedObjects, 0);

            reOrderMeanList();

            return meanOfTransportLogics;
        }
        catch(Exception  e){
                return (new CheapestCore()).getMeanOfTransports(meansOfTransport, startingLocation, endingLocation, departureTime, arrivalTime, timeRequest);
            }
    }

    private void clearInvalidObject(List<GoogleResponseMappedObject> googleResponseMappedObjects) {
        List<GoogleResponseMappedObject> readList = new ArrayList<>();
        readList.addAll(googleResponseMappedObjects);

        for(GoogleResponseMappedObject googleResponseMappedObject: readList)
            if(googleResponseMappedObject.getRoutes() == null ||
                    googleResponseMappedObject.getLeg() == null ||
                    googleResponseMappedObject.getSteps() == null ||
                    googleResponseMappedObject.getSteps().isEmpty()) {
                googleResponseMappedObjects.remove(googleResponseMappedObject);
            }
    }

    /**
     *
     */
    private void reOrderMeanList() {
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
        String best = googleResponseMappedObjects.get(0).getFirstTravelMode().toLowerCase();
        int bestIndex = 0;


        for(GoogleResponseMappedObject grmo: googleResponseMappedObjects){
            if(grmo.getDuration() < googleResponseMappedObjects.get(bestIndex).getDuration()){
                best = grmo.getFirstTravelMode();
                bestIndex = googleResponseMappedObjects.indexOf(grmo);
            }
        }

        if(bestIndex != index) {
            meanTypes.remove(best);
            meanTypes.add(index, best);
            googleResponseMappedObjects.remove(bestIndex);
        }
        else if(!meanTypes.contains(best))
            meanTypes.add(index, best);

        if(index < meanTypes.size() -2)
            orderTypeByTime(googleResponseMappedObjects, index + 1);
    }
}
