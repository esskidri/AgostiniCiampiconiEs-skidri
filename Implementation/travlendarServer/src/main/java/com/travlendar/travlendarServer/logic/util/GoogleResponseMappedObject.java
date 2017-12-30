package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * This class contain all the attributes needed to grant an useful mapping with the google response
 * ObjectMapper of the jackson databind library contain the method to grant a correct mapping
 * This class will also implement useful method to extract important information for our algorithm
 */
public class GoogleResponseMappedObject implements Serializable {
    private List<GeocodedWaypoint> geocoded_waypoints;
    private List<Route> routes;

    public GoogleResponseMappedObject(List<GeocodedWaypoint> geocoded_waypoints, List<Route> routes, Timestamp departingTime, Timestamp arrivalTime, String status, boolean partialSolution) {
        this.geocoded_waypoints = geocoded_waypoints;
        this.routes = routes;
        this.departingTime = departingTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.partialSolution = partialSolution;
    }

    public GoogleResponseMappedObject() {
    }

    //supporting attribute
    private Timestamp departingTime;
    private Timestamp arrivalTime;
    private String status;
    private boolean partialSolution = false;



    /*** Getter and setter for object mapping ***/

    public List<GeocodedWaypoint> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(List<GeocodedWaypoint> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public String getStatus() {
        return status;
    }


    /*** Supporting methods for logic ***/
    public Coordinates getEndingLocation() {
        return getLeg().getEnd_location();
    }

    public Coordinates getStartingLocation() {
        return getLeg().getStart_location();
    }

    public void setStartingLocation(Coordinates coordinates){
        getLeg().setStart_location(coordinates);
    }

    public void setDepartingTime(Timestamp departingTime) {
        this.departingTime = departingTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //TODO add update methods

    public Timestamp getDepartingTime() {
        return departingTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public long getDistance(){
        return getLeg().getDistance().getValue();
    }

    public long getDuration(){
        return getLeg().getDuration().getValue();
    }

    public boolean isPartialSolution() {
        return partialSolution;
    }

    public List<Step> getSteps(){
        return getLeg().getSteps();
    }

    public String getFirstTravelMode(){
        return getSteps().get(0).getTravel_mode();
    }

    public Leg getLeg(){
        return routes.get(0).getLegs().get(0);
    }



    /***
     * Logic Methods
     */

    /***
     *
     * Check if the response is complete by the meaning required
     * If not it call cutWay Method
     *
     * @param meanOfTransport
     * @throws MeanNotAvailableException
     */

    public void checkCompleteness(String meanOfTransport, Timestamp arrivalTime) throws MeanNotAvailableException {
        boolean isPartial = false;

        boolean meanFounded = false;
        if(status == null || status.equals("OK")) {
            if(this.getLeg().getArrival_time() != null)
                setArrivalTime(new Timestamp(this.getLeg().getArrival_time().getValue()*1000));
            else {
                setArrivalTime(arrivalTime);
                InfoPair arrival_time = new InfoPair();
                arrival_time.setValue(arrivalTime.getTime()/1000);
                this.getLeg().setArrival_time(arrival_time);
            }

            int i = 0;
            for (Step step : getSteps()) {
                if(step.getTravel_mode().equals(meanOfTransport.toUpperCase()))
                    meanFounded = true;
                if (!step.getTravel_mode().equals(meanOfTransport.toUpperCase()) && meanFounded) {
                    isPartial = true;
                    break;
                }
                i++;
            }
            if(isPartial)
                cutWay(i, getSteps().size());

            setDepartingTime(new Timestamp((((this.getLeg().getArrival_time().getValue() - getDuration())*1000))));

        }
        else if(status.equals("NOT_FOUND") || status.equals("ZERO_RESULTS") || status.equals("MAX_WAYPOINTS_EXCEEDED") || status.equals("MAX_ROUTE_LENGTH_EXCEEDED") || status.equals("INVALID_REQUEST") || status.equals("REQUEST_DENIED") || status.equals("UNKNOWN_ERROR") )
            throw new MeanNotAvailableException(status);
        else if(status.equals("OVER_QUERY_LIMIT"))
            throw new MeanNotAvailableException("Maximum number of daily query reached, service will not be available since tomorrow");


    }

    public void searchPublicLine(){
        int i = 0;


        for(Step step: getSteps()){
            if(step.getTravel_mode().equals("TRANSIT")){
                setStartingLocation(step.getStart_location());
                break;
            }
            i++;
        }
        if(i != 0)
            cutWay(0, i);
    }

    /**
     *
     * This method remove the steps that does not use the desired mean
     * and recalculates the fundamental parameters (duration, length etc..)
     *
     * @param i index from which the google response must be cut
     */
    private void cutWay(int i, int j) {
        if(i != 0)
            partialSolution = true;
        long dateTime;
        long length;
        long timeDuration;
        InfoPair duration= new InfoPair();
        InfoPair distance = new InfoPair();

        timeDuration = getLeg().getDuration().getValue();
        length = getLeg().getDistance().getValue();

        if(i == 0)
            dateTime =  arrivalTime.getTime()/1000 - timeDuration;
        else
            dateTime =  arrivalTime.getTime()/1000;

        for(Step step: getSteps().subList(i, j)){
            if(i == 0)
                dateTime += step.getDuration().getValue();
            else
                dateTime -= step.getDuration().getValue();

            timeDuration -= step.getDuration().getValue();
            length -= step.getDuration().getValue();
        }

        if(i == 0)
            getLeg().setSteps(getSteps().subList(j,getSteps().size()));
        else {
            getLeg().setSteps(getSteps().subList(0, i));
            getLeg().setEnd_location(getSteps().get(getSteps().size() - 1).getEnd_location());
            getLeg().setEnd_address("modified"); //TODO secondario
        }

        duration.setValue(timeDuration);
        duration.setText(" modified"); //TODO secondario

        distance.setValue(length);
        distance.setText(" modified"); //TODO secondario

        getLeg().setDuration(duration);

        if(i == 0)
            setDepartingTime(new Timestamp(dateTime*1000));
        else
            setArrivalTime(new Timestamp(dateTime*1000));

    }


}
