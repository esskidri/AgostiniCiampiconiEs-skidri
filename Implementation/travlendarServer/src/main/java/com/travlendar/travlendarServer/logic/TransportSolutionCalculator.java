package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.exceptions.CannotArriveInTimeException;
import com.travlendar.travlendarServer.logic.exceptions.EarlyStartException;
import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.exceptions.NoMeanAvailableExpection;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSegmentLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.domain.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.List;

public class TransportSolutionCalculator {
    private CalculatorCore calculatorCore;
    private List<TransportSegmentLogic> transportSegments;

    public TransportSolutionCalculator(CalculatorCore calculatorCore) {
        this.calculatorCore = calculatorCore;
    }

    public TransportSolutionLogic calculateSolution(Coordinates startingLocation, Coordinates endingLocation, Timestamp startingTime, Timestamp arrivalTime, UserPreferences userPreferences){
        List<MeanOfTransportLogic> meansOfTransport = calculatorCore.getMeanOfTransports(userPreferences);

        try {
            calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport);
        } catch (NoMeanAvailableExpection noMeanAvailableExpection) {
            //TODO
            noMeanAvailableExpection.printStackTrace();
        } catch (CannotArriveInTimeException e) {
            //TODO
            e.printStackTrace();
        }

        TransportSolutionLogic transportSolution = new TransportSolution();
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
        try {
            if (isMeanAvailablePrivately(meansOfTransport.get(0))) {
                googleResponseMappedObject = callGoogleAPI(startingLocation.toHttpsFormat(), endingLocation.toHttpsFormat(), meansOfTransport.get(0).getTypeOfTransport().toHttpsFormat(), arrivalTime.toString());
            } else {
                Coordinates mediumLocation = getLocationByExternalAPI(meansOfTransport.get(0));
                googleResponseMappedObject = callGoogleAPI(mediumLocation.toHttpsFormat(), endingLocation.toHttpsFormat(), meansOfTransport.get(0).getTypeOfTransport().toHttpsFormat(), arrivalTime.toString());
                if(googleResponseMappedObject.getDepartingTime().compareTo(startingTime) < 0)
                    throw new EarlyStartException();
                calculateSegment(startingLocation, mediumLocation, startingTime, googleResponseMappedObject.getDepartingTime(), meansOfTransport.subList(1, meansOfTransport.size()));
            }
            insertTransportSegments(googleResponseMappedObject, meansOfTransport.get(0));
            if(googleResponseMappedObject.isPartialSolution())
                calculateSegment(googleResponseMappedObject.getEndingLocation(), endingLocation, googleResponseMappedObject.getArrivalTime(), arrivalTime,meansOfTransport.subList(1, meansOfTransport.size()));
        } catch (MeanNotAvailableException e) {
            if(meansOfTransport.size() != 0)
                calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport.subList(1, meansOfTransport.size()));
            else
                throw new NoMeanAvailableExpection();
        }
        catch (EarlyStartException e){
            if(meansOfTransport.size() != 0)
                calculateSegment(startingLocation, endingLocation, startingTime, arrivalTime, meansOfTransport.subList(1, meansOfTransport.size()));
            else
                throw new CannotArriveInTimeException();
        }
    }

    private void insertTransportSegments(GoogleResponseMappedObject googleResponseMappedObject, MeanOfTransportLogic meanOfTransport) {
        TransportSegmentLogic transportSegment = new TransportSegment();

        transportSegment.setMeanOfTransport(meanOfTransport);

        transportSegment.setOrigin(googleResponseMappedObject.getStartingLocation());
        transportSegment.setDestination(googleResponseMappedObject.getEndingLocation());

        int i = 0;

        for(TransportSegmentLogic transportSegment1: transportSegments) {
            if (transportSegment1.isAdiacent(googleResponseMappedObject.getStartingLocation())) {
                break;
            }
            i++;
        }

        transportSegments.add(transportSegment);


    }

    @NotNull
    private Coordinates getLocationByExternalAPI(MeanOfTransportLogic meanOfTransport) throws MeanNotAvailableException {
        return new Coordinates();
    }

    /**
     * @param startingLocation
     * @param endingLocation
     * @param meanOfTransport
     * @param arrivalTime
     * @return
     *
     * This method call the google API to request the calculation of a route between two points
     * with a specific mean of transport
     *
     */
    private GoogleResponseMappedObject callGoogleAPI(String startingLocation, String endingLocation, String meanOfTransport, String arrivalTime) throws EarlyStartException {
        GoogleResponseMappedObject googleResponseMappedObject;

        googleResponseMappedObject = GoogleAPIHandler.askGoogle(startingLocation, endingLocation, meanOfTransport, arrivalTime);
        googleResponseMappedObject.checkCompleteness(meanOfTransport);

        return googleResponseMappedObject;
    }

    /**
     * @param meanOfTransport
     * @return This method check if the user own the mean of transport and if he can use it
     */
    @Contract(pure = true)
    private boolean isMeanAvailablePrivately(MeanOfTransportLogic meanOfTransport) {
        //TODO implements memory of passed trip
        //TODO Whole class, not required for current phase of developing
        return true;
    }
}
