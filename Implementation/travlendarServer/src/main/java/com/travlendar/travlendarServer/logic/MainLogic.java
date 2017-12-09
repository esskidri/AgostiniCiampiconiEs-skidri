package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.logic.util.EventGraph;
import com.travlendar.travlendarServer.model.domain.TransportSolution;
import com.travlendar.travlendarServer.model.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MainLogic {

    public List<TransportSolutionLogic> calculateTransportSolutions(List<EventLogic> events, UserLogic user){
        EventGraph eventGraph = EventConnector.findConnection(events);
        List<TransportSolutionLogic> transportSolutions = new ArrayList<>();

        for(EventLogic outGoing: events){
            for(EventLogic inGoing: eventGraph.edges().get(outGoing))
                transportSolutions.add((new TransportSolutionCalculator(Policy.GREEN.getCore())).calculateSolution( outGoing.getCoordinates(), inGoing.getCoordinates(), outGoing.getEndDate(), inGoing.getStartDate(), user.getUserPreferences()));
        }

        return transportSolutions;
    }
}
