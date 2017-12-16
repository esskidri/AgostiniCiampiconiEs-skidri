package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.EventGraph;
import com.travlendar.travlendarServer.model.Policy;

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
    public List<TransportSolutionLogic> calculateTransportSolutions(List<EventLogic> events, UserLogic user){
        EventGraph eventGraph = EventConnector.findConnection(events);
        List<TransportSolutionLogic> transportSolutions = new ArrayList<>();

        for(EventLogic outGoing: events){
            for(EventLogic inGoing: eventGraph.edges().get(outGoing)) {
                TransportSolutionLogic transportSolutionLogic = (new TransportSolutionCalculator(user.getPolicy().getCore())).calculateSolution(outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), inGoing.getStartDate(), user);
                transportSolutionLogic.setStartEvent(outGoing);
                transportSolutionLogic.setEndEvent(inGoing);
                transportSolutions.add(transportSolutionLogic);
            }
        }

        return transportSolutions;
    }
}
