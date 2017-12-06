package com.travlendar.travlendarServer.logic;




import com.travlendar.travlendarServer.model.Event;
import com.travlendar.travlendarServer.model.TransportSolution;
import com.travlendar.travlendarServer.util.EventGraph;


import java.util.HashMap;
import java.util.List;

public class EventConnector {
    private EventGraph eventGraph;
    private List<TransportSolution> transportSolutions;

    public EventConnector(List<Event> events) {
        eventGraph = new EventGraph(events, new HashMap<>());
    }

    public void findConnection(){
        int i = 0;

        for(Event event: eventGraph.nodes()){
            if(event.getEndEvent()){
                insertHomeEvent(eventGraph,event);
            }
            else if(i != eventGraph.nodes().size())
                if(event.compareTo(eventGraph.nodes().get(i + 1)) != 0)
                    eventGraph.connect(event, eventGraph.nodes().get(i + 1));
            i++;
        }

        for(Event event: eventGraph.nodes()){
            for(EventGraph.Edge edge: eventGraph.edgesFrom(event)){
                if(event.overlapping(edge.getE())){
                    for(EventGraph.Edge edge1: eventGraph.edgesFrom(edge.getS()))
                        eventGraph.connect(event, edge1.getS());
                }
            }
        }

    }

    private void insertHomeEvent(EventGraph eventGraph, Event endEvent){
        //TODO
    }

    class Home extends Event{
        //TODO
        /*@Override
        public int compareTo(Event e) {
            if(this.getStartTime().compareTo(e.getStartTime())!= 0)
                return this.startTime.compareTo(e.startTime);
            else
                return this.endTime.compareTo(endTime);
        }*/
    }
}
