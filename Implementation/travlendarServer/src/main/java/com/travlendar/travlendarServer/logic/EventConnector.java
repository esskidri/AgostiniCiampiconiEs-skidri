package com.travlendar.travlendarServer.logic;




import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.logic.util.EventGraph;


import java.util.HashMap;
import java.util.List;

public class EventConnector {

    private EventConnector(List<EventLogic> events) {

    }

    public static EventGraph findConnection(List<EventLogic> events){
        EventGraph eventGraph = new EventGraph(events, new HashMap<>());

        int i = 0;

        for(EventLogic event: eventGraph.nodes()){
            if(event.isEndEvent()){
                insertHomeEvent(eventGraph,event);
            }
            else if(i != eventGraph.nodes().size())
                if(event.compareTo(eventGraph.nodes().get(i + 1)) != 0)
                    eventGraph.connect(event, eventGraph.nodes().get(i + 1));
            i++;
        }

        for(EventLogic event: eventGraph.nodes()){
            for(EventGraph.Edge edge: eventGraph.edgesFrom(event)){
                if(event.overlapping(edge.getE())){
                    for(EventGraph.Edge edge1: eventGraph.edgesFrom(edge.getS()))
                        eventGraph.connect(event, edge1.getS());
                }
            }
        }

        return eventGraph;
    }

    private static void insertHomeEvent(EventGraph eventGraph, EventLogic endEvent){
        //TODO
    }

    class Home extends Event {
        //TODO
        /*@Override
        public int compareTo(Event e) {
            if(this.getStartDate().compareTo(e.getStartDate())!= 0)
                return this.startTime.compareTo(e.startTime);
            else
                return this.endTime.compareTo(endTime);
        }*/
    }
}
