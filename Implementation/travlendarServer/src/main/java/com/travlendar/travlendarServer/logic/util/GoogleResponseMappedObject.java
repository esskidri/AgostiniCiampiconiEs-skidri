package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * This class contain all the attributes needed to grant an useful mapping with the google response
 * ObjectMapper of the jackson databind library contain the method to grant a correct mapping
 * This class will also implement useful method to extract important information for out algorithm
 */
public class GoogleResponseMappedObject implements Serializable {
    List<GeocodedWaypoint> geocoded_waypoints;
    List<Route> routes;

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

    public Timestamp getDepartingTime() {
        return departingTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public int getDistance(){
        return getLeg().getDistance().getValue();
    }

    public int getDuration(){
        return getLeg().getDuration().getValue();
    }

    public boolean isPartialSolution() {
        return partialSolution;
    }

    private List<Step> getSteps(){
        return getLeg().getSteps();
    }

    private Leg getLeg(){
        return routes.get(0).getLegs().get(0);
    }

    public void checkCompleteness(String meanOfTransport) throws MeanNotAvailableException {
        if(status == null || status.equals("OK")) {
            int i = 0;
            for (Step step : getSteps()) {
                if (!step.getTravel_mode().equals(meanOfTransport.toUpperCase())) {
                    cutWay(i);
                    break;
                }
                i++;
            }
        }
        else if(status.equals("NOT_FOUND") || status.equals("ZERO_RESULTS") || status.equals("MAX_WAYPOINTS_EXCEEDED") || status.equals("MAX_ROUTE_LENGTH_EXCEEDED") || status.equals("INVALID_REQUEST") || status.equals("REQUEST_DENIED") || status.equals("UNKNOWN_ERROR") )
            throw new MeanNotAvailableException(status);
        else if(status.equals("OVER_QUERY_LIMIT"))
            throw new MeanNotAvailableException("Maximum number of daily query reached, service will not be available since tomorrow");

    }

    public void searchPublicLine(){
        for(Step step: getSteps()){
            if(step.getTravel_mode().equals("TRANSIT")){
                setStartingLocation(step.getStart_location());
                break;
            }
        }
    }

    private void cutWay(int i) {
        partialSolution = true;
        int time;
        InfoPair duration= new InfoPair();

        time = getLeg().getDuration().getValue();

        for(Step step: getSteps().subList(i, getSteps().size())){
            time -= step.getDuration().getValue();
        }

        getLeg().setSteps(getSteps().subList(0, i));
        getLeg().setEnd_location(getSteps().get(getSteps().size() -1).getEnd_location());
        getLeg().setEnd_address("modified"); //TODO secondario

        duration.setValue(time);
        duration.setText(" modified"); //TODO secondario

        getLeg().setDuration(duration);

    }
}
