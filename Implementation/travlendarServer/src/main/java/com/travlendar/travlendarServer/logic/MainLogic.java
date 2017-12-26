package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.EventGraph;
import com.travlendar.travlendarServer.logic.util.TimeRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainLogic {

    /**
     *
     * Principal method that is called by Data Manager when transport solutions is needed.
     * The Algorithm that this method mask receive a list of ordered events and the user preferences
     * First of all the connection between events are recognized, then the solution calculated.
     *
     * @param events Ordered List of subsequent events interface given by the Data Manager (?) //TODO politics of giving list of event
     * @param user User interface that have the event in the calendar, this parameter is useful to query the user preferences
     * @return
     */
    public static List<TransportSolutionLogic> calculateTransportSolutions(List<EventLogic> events, UserLogic user){
        EventGraph eventGraph = EventConnector.findConnection(events);
        List<TransportSolutionLogic> transportSolutions = new ArrayList<>();
        TransportSolutionLogic transportSolutionLogic;
        Timestamp arrivalAtHome = null;
        List<MeanOfTransportLogic> meansOfTransport = user.getMeanPreferences();
        List<MeanOfTransportLogic> meansOfTransportForSolution;

        for(EventLogic outGoing: events){
            for(EventLogic inGoing: eventGraph.edges().get(outGoing)) {
                if(inGoing.atHome()){
                    if(eventGraph.edges().get(inGoing).size() != 0);
                    //TODO correct
                        EventLogic eventAfter = eventGraph.edges().get(inGoing).get(0);
                    meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport, outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), eventAfter.getStartDate(), TimeRequest.DEPARTURE);
                    transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.DEPARTURE)).calculateSolution(outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), eventAfter.getStartDate(), meansOfTransportForSolution);
                    arrivalAtHome = transportSolutionLogic.getArrivalTime();
                }
                if(outGoing.atHome()) {
                    //I assume that this algorithm is never asked to calculate solution from a home event when before
                    //it didn't calculate solution to reach home, this is granted by the structure and the behave of
                    // event connector
                    meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport, outGoing.getCoordinates(), inGoing.getCoordinates(), arrivalAtHome, inGoing.getStartDate(), TimeRequest.ARRIVAL);
                    transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.ARRIVAL)).calculateSolution(outGoing.getCoordinates(), inGoing.getCoordinates(), arrivalAtHome, inGoing.getStartDate(), meansOfTransportForSolution);
                }
                else{
                    meansOfTransportForSolution = user.getPolicy().getCore().getMeanOfTransports(meansOfTransport, outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), inGoing.getStartDate(), TimeRequest.ARRIVAL);
                    transportSolutionLogic = (new TransportSolutionCalculator(TimeRequest.ARRIVAL)).calculateSolution(outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), inGoing.getStartDate(), meansOfTransportForSolution);
                }
                transportSolutionLogic.setStartEvent(outGoing);
                transportSolutionLogic.setEndEvent(inGoing);
                setMeansOfTransport(transportSolutionLogic, meansOfTransport, user);
                transportSolutions.add(transportSolutionLogic);
            }
        }
        return transportSolutions;
    }

    private void setMeansOfTransport(TransportSolutionLogic transportSolutionLogic, List<MeanOfTransportLogic> meansOfTransport, UserLogic user) {
        List<MeanOfTransportLogic> readList = new ArrayList<>();
        readList.addAll(meansOfTransport);

        if(transportSolutionLogic.getEndEvent().atHome())
            meansOfTransport = user.getMeanPreferences();
        else{
            for(MeanOfTransportLogic meanOfTransportLogic: readList)
                if(meanOfTransportLogic.isPrivate() && !transportSolutionLogic.getPrivateMeansUsed().contains(meanOfTransportLogic))
                    meansOfTransport.remove(meanOfTransportLogic);
        }
    }
}
