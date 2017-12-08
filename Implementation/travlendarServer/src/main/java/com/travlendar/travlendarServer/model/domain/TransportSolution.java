package com.travlendar.travlendarServer.model.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transport_solution")
public class TransportSolution implements Serializable {

    @OneToOne
    @JoinColumn(name="event_id_1",insertable = false, updatable = false)
    private Event event1;

    @OneToOne
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
}
