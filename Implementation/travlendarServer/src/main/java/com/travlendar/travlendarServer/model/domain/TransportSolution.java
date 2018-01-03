package com.travlendar.travlendarServer.model.domain;


import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSegmentLogic;
import com.travlendar.travlendarServer.logic.modelInterface.TransportSolutionLogic;
import com.travlendar.travlendarServer.model.clientModel.TransportSegmentClient;
import com.travlendar.travlendarServer.model.clientModel.TransportSolutionClient;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transport_solution")
public class TransportSolution extends AbstractEntity implements Serializable, TransportSolutionLogic {

    @OneToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="event_id_1",insertable = false, updatable = false)
    private Event event1;

    @OneToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="event_id_2",insertable = false, updatable = false)
    private Event event2;

    @EmbeddedId
    private TransportSolutionId transportSolutionId;

    @OneToMany(mappedBy = "transportSolution")
    private List<TransportSegment> transportSegments;

    public Event getEvent1() {
        return event1;
    }

    public void setEvent1(Event event1) {
        this.event1 = event1;
    }

    public Event getEvent2() {
        return event2;
    }

    public void setEvent2(Event event2) {
        this.event2 = event2;
    }

    public TransportSolutionId getTransportSolutionId() {
        return transportSolutionId;
    }

    public void setTransportSolutionId(TransportSolutionId transportSolutionId) {
        this.transportSolutionId = transportSolutionId;
    }

    public List<TransportSegment> getTransportSegments() {
        return transportSegments;
    }

    public void setTransportSegments(List<TransportSegment> transportSegments) {
        this.transportSegments = transportSegments;
    }

    public TransportSolution(){}

    public TransportSolution(Event event1, Event event2, TransportSolutionId transportSolutionId, List<TransportSegment> transportSegments) {
        this.event1 = event1;
        this.event2 = event2;
        this.transportSolutionId = transportSolutionId;
        this.transportSegments = transportSegments;
    }

    /***
     *  Logic interface Implementation
     */

    /***
     *
     *
     *
     * @param transportSegmentsLogic
     */

    @Override
    public void setTransportSegmentsByLogic(List<TransportSegmentLogic> transportSegmentsLogic) {
        List<TransportSegment> transportSegments = new ArrayList<>();

        for(TransportSegmentLogic transportSegmentLogic : transportSegmentsLogic)
            transportSegments.add((TransportSegment) transportSegmentLogic);

        setTransportSegments(transportSegments);
    }

    @Override
    public void setStartEvent(EventLogic event) {
        event1 = (Event) event;
    }

    @Override
    public void setEndEvent(EventLogic event) {
        event2 = (Event) event;
    }

    @Override
    public EventLogic getStartEvent() {
        return event1;
    }

    @Override
    public EventLogic getEndEvent() {
        return event2;
    }

    @Override
    public Timestamp getArrivalTime(){
        if(!transportSegments.isEmpty())
            return transportSegments.get(transportSegments.size() -1).getArrivalTime();
        else
            return null;
    }

    @Override
    public List<MeanOfTransportLogic> getPrivateMeansUsed() {
        List<MeanOfTransportLogic> meansOfTransportUsed = new ArrayList<>();
        for(TransportSegment transportSegment: transportSegments){
            if(transportSegment.getPrivateTransport() != null && !meansOfTransportUsed.contains(transportSegment.getPrivateTransport()))
                meansOfTransportUsed.add(transportSegment.getPrivateTransport());
        }

        return meansOfTransportUsed;
    }

    @Override
    public boolean isEmpty() {
        return transportSegments.isEmpty();
    }

    public TransportSolutionClient getTransportSolutionClient(){
        List<TransportSegmentClient> transportSegmentClients=new ArrayList<>();
        for(TransportSegment t:transportSegments){
            transportSegmentClients.add(t.getTransportSegmentClient());
        }
        return new TransportSolutionClient(event1.getId(),event2.getId(),transportSegmentClients);
    }
}
