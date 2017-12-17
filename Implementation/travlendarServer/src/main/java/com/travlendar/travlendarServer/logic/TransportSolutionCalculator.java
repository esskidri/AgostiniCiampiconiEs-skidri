package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.exceptions.CannotArriveInTimeException;
import com.travlendar.travlendarServer.logic.exceptions.EarlyStartException;
import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.exceptions.NoMeanAvailableExpection;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSegmentLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.MeanType;
import com.travlendar.travlendarServer.model.domain.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransportSolutionCalculator {
    private CalculatorCore calculatorCore;
    private List<TransportSegmentLogic> transportSegments = new ArrayList<>();
    private List<MeanOfTransportLogic> meansOfTransport;

    public TransportSolutionCalculator(CalculatorCore calculatorCore) {
        this.calculatorCore = calculatorCore;
    }

    public TransportSolutionLogic calculateSolution(Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime, UserLogic userLogic) {
        this.meansOfTransport = calculatorCore.getMeanOfTransports(userLogic, startingLocation, endingLocation, startingTime, arrivalTime);

        try {
            calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport);
        } catch (NoMeanAvailableExpection noMeanAvailableExpection) {
            //TODO
            System.out.println("NoMeanAvailableException");
            noMeanAvailableExpection.printStackTrace();
        } catch (CannotArriveInTimeException e) {
            //TODO
            System.out.println("CannotArriveInTimeException");
            e.printStackTrace();
        }

        TransportSolutionLogic transportSolution = new TransportSolution();

        orderSegments();

        transportSolution.setTransportSegmentsByLogic(transportSegments);

        return transportSolution;
    }


    /**
     * @param startingLocation
     * @param endingLocation
     * @param meansOfTransport
     * @param arrivalTime      This method calculate a route segment of the transport solution with the desired mean.
     *                         If this mean of transport is contained in the user private mean of transport and is available
     *                         for the specific situation the Google API will be directly called. If the output of the
     *                         google API cover all the trip between the two location with the desired mean the method
     *                         has ended his task, in case not the algorithm call itself with the starting location, as where
     *                         the Google API indicates that the desired mean of transport will not be used, and with ending
     *                         location the same ending location which was initially passed to the algorithm (l1).
     *                         Then he consider as the google solution only for the subroute where the desired mean
     *                         is used. In case the mean of transport desired to use is a public or a sharing one the
     *                         specific External API will be called and a location is returned (of a bus stop or of the
     *                         nearest car to be reserved) and used as a location l2. First of all the algorithm recall
     *                         itself passing to it two location (l0, l2). Then call the Google API with the mean
     *                         (in this case a sharing/public one, but at this level is not important) and follow
     *                         the same behavior after the Google API call as described above.
     */
    private void calculateSegment(Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime, List<MeanOfTransportLogic> meansOfTransport) throws NoMeanAvailableExpection, CannotArriveInTimeException {
        GoogleResponseMappedObject googleResponseMappedObject;
        if (meansOfTransport.size() == 0)
            throw new NoMeanAvailableExpection();
        try {
            if (isMeanAvailablePrivately(meansOfTransport.get(0))) {
                //This mean is private and the user can use it by the previous movements
                googleResponseMappedObject = callGoogleAPI(startingLocation, endingLocation, meansOfTransport.get(0), arrivalTime);
            } else {
                //The mean is not a private mean, so the user must reach a medium location to use it
                Coordinates mediumLocation;


                //If it's a mean of the public transport (Bus, Metro, Tram etc) this is handled by the google API
                if (meansOfTransport.get(0).getTypeOfTransport() == MeanType.BUS) {
                    googleResponseMappedObject = callGoogleAPI(startingLocation, endingLocation, meansOfTransport.get(0), arrivalTime);
                    googleResponseMappedObject.searchPublicLine(arrivalTime);
                    mediumLocation = googleResponseMappedObject.getStartingLocation();
                }
                else {
                    //Else the medium location is obtained by the position of a private sharing services (MoBike, Enjoy etc)
                    mediumLocation = getLocationByExternalAPI(meansOfTransport.get(0), startingLocation, endingLocation, arrivalTime);
                    googleResponseMappedObject = callGoogleAPI(mediumLocation, endingLocation, meansOfTransport.get(0), arrivalTime);
                }
                if (googleResponseMappedObject.getDepartingTime().compareTo(startingTime) < 0)
                    throw new EarlyStartException();
                calculateSegment(startingLocation, mediumLocation, startingTime, googleResponseMappedObject.getDepartingTime(), meansOfTransport.subList(1, meansOfTransport.size()));
            }

            //the calculated segment is inserted
            insertTransportSegments(googleResponseMappedObject, meansOfTransport.get(0));
            if (googleResponseMappedObject.isPartialSolution()) {
                //If google make you change your mean, we recalculate by the preferences of the user
                List<MeanOfTransportLogic> meanOfTransportLogics = new ArrayList<>();
                meanOfTransportLogics.addAll(this.meansOfTransport);
                meanOfTransportLogics.remove(meansOfTransport.get(0));
                for(MeanOfTransportLogic meanOfTransportLogic: this.meansOfTransport)
                    if(!isMeanAvailablePrivately( meanOfTransportLogic))
                        meanOfTransportLogics.remove(meanOfTransportLogic);
                calculateSegment(googleResponseMappedObject.getEndingLocation(), endingLocation, googleResponseMappedObject.getArrivalTime(), arrivalTime, meanOfTransportLogics);
            }
        } catch (MeanNotAvailableException e) {
            //In case the mean is not available
            if (meansOfTransport.size() != 0)
                calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport.subList(1, meansOfTransport.size()));
            else
                //In case there's no more mean to be used for the solution
                throw new NoMeanAvailableExpection();
        } catch (EarlyStartException e) {
            //In case the user must leave the event before the end of the antecedent event
            if (meansOfTransport.size() != 0)
                calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport.subList(1, meansOfTransport.size()));
            else
                //In case there's no more mean to be used for the solution
                throw new CannotArriveInTimeException();
        }
    }

    private void insertTransportSegments(GoogleResponseMappedObject googleResponseMappedObject, MeanOfTransportLogic meanOfTransport) {
        TransportSegmentLogic transportSegment = new TransportSegment();

        transportSegment.setMeanOfTransport(meanOfTransport);

        transportSegment.setOrigin(googleResponseMappedObject.getStartingLocation());
        transportSegment.setDestination(googleResponseMappedObject.getEndingLocation());
        transportSegment.setDistance(googleResponseMappedObject.getDistance());
        transportSegment.setDuration(googleResponseMappedObject.getDuration());

        int i = 0;

        for (TransportSegmentLogic transportSegment1 : transportSegments) {
            if (transportSegment1.isAdiacent(googleResponseMappedObject.getStartingLocation())) {
                break;
            }
            i++;
        }

        transportSegments.add(transportSegment);
    }

    /**
     * Reorder the transport solution's segments making them adiacent
     */
    private void orderSegments() {
        int i = 0;
        int j = 1;


        TransportSegmentLogic swapSegment;

        while(j < transportSegments.size()){
            if(transportSegments.get(j).getOrigin().equals(transportSegments.get(i).getDestination())){
                swapSegment = transportSegments.get(j);
                transportSegments.remove(swapSegment);
                transportSegments.add(i +1, swapSegment);
                i = i + 1;
                j = i + 1;
            }
            j++;
        }

        j = transportSegments.size() -1;

        while(j > i){
            if(transportSegments.get(j).getDestination().equals(transportSegments.get(0).getOrigin())){
                swapSegment = transportSegments.get(j);
                transportSegments.remove(swapSegment);
                transportSegments.add(0, swapSegment);
                i = i + 1;
                j = transportSegments.size();
            }
            j--;
        }
    }



    @NotNull
    private Coordinates getLocationByExternalAPI(MeanOfTransportLogic meanOfTransport, Coordinates startingLocation, Coordinates endingLocation, Timestamp arrivalTime) throws MeanNotAvailableException {

        throw new MeanNotAvailableException();
    }

    /**
     * @param startingLocation
     * @param endingLocation
     * @param meanOfTransport
     * @param arrivalTime
     * @return This method call the google API to request the calculation of a route between two points
     * with a specific mean of transport
     */
    private GoogleResponseMappedObject callGoogleAPI(Coordinates startingLocation, Coordinates endingLocation, MeanOfTransportLogic meanOfTransport, Timestamp arrivalTime) throws EarlyStartException, MeanNotAvailableException {
        GoogleResponseMappedObject googleResponseMappedObject;

        googleResponseMappedObject = GoogleAPIHandler.askGoogle(startingLocation, endingLocation, meanOfTransport, arrivalTime);
        googleResponseMappedObject.checkCompleteness(meanOfTransport.getTypeOfTransport().toHttpsFormat(), arrivalTime);

        return googleResponseMappedObject;
    }

    /**
     * @param meanOfTransport
     * @return This method check if the user own the mean of transport and if he can use it
     */
    @Contract(pure = true)
    private boolean isMeanAvailablePrivately(MeanOfTransportLogic meanOfTransport) throws MeanNotAvailableException{
        if (!meanOfTransport.isPrivate())
            return false;
        //TODO throw exception in case the mean cannot be used
        return true;
    }
}
