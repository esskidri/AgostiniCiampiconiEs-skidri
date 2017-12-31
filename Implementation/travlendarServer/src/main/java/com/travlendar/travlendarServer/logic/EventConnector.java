package com.travlendar.travlendarServer.logic;




import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.logic.util.EventGraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventConnector {

    private EventConnector(List<EventLogic> events) {

    }

    public static EventGraph findConnection(List<EventLogic> events){
        List<EventLogic> eventsWithHome = new ArrayList<>();
        EventGraph eventGraph = new EventGraph(events, new HashMap<>());
        eventsWithHome.addAll(events);
        for(EventLogic event: events)
            eventGraph.edges().put(event, new ArrayList<>());

        int i = 0;

        for(EventLogic event: eventGraph.nodes()){
            if(event.isEndEvent() &&
                    i != eventGraph.nodes().size() -1 &&
                    !eventGraph.nodes().get(i +1).atHome()){
                insertHomeEvent(eventGraph, event, eventsWithHome);
            }
            else if(i < eventGraph.nodes().size() - 1)
                if(event.compareTo(eventGraph.nodes().get(i + 1)) != 0)
                    eventGraph.connect(event, eventGraph.nodes().get(i + 1));
            i++;
        }

        eventGraph.nodes().clear();
        eventGraph.nodes().addAll(eventsWithHome);


        for(EventLogic event: eventGraph.nodes()){
            for(EventGraph.Edge edge: eventGraph.edgesFrom(event)){
                if(event.overlapping(edge.getE()) && !edge.getE().atHome() && !event.atHome()){
                    for(EventGraph.Edge edge1: eventGraph.edgesFrom(edge.getS()))
                        eventGraph.connect(event, edge1.getE());
                }
            }
        }

        return eventGraph;
    }


    private static void insertHomeEvent(EventGraph eventGraph, EventLogic endEvent, List<EventLogic> eventWithHome){
        Event homeEvent = new Event(endEvent.getStartDate(),endEvent.getEndDate(),endEvent.getCurrentHome().getLat(),endEvent.getCurrentHome().getLng(),"CASA", "CASA", false);
        homeEvent.setAtHome(true);

        eventWithHome.add(eventWithHome.indexOf(endEvent) +1,homeEvent);
        eventGraph.edges().put(homeEvent, new ArrayList<>());
        eventGraph.connect(endEvent, homeEvent);
        eventGraph.connect(homeEvent, eventWithHome.get(eventWithHome.indexOf(homeEvent) + 1));

    }

}
