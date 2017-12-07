package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transport_solution")
public class TransportSolution implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name="event_id_1")
    private Event event1;

    @Id
    @OneToOne
    @JoinColumn(name="event_id_2")
    private Event event2;

    @OneToMany(mappedBy = "transportSolution1")
    private TransportSegment transportSolution1;

    @OneToMany(mappedBy = "transportSolution2")
    private List<TransportSegment> transportSolution2;

    public TransportSolution(Event event1, Event event2, TransportSegment transportSolution1, List<TransportSegment> transportSolution2) {
        this.event1 = event1;
        this.event2 = event2;
        this.transportSolution1 = transportSolution1;
        this.transportSolution2 = transportSolution2;
    }

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

    public TransportSegment getTransportSolution1() {
        return transportSolution1;
    }

    public void setTransportSolution1(TransportSegment transportSolution1) {
        this.transportSolution1 = transportSolution1;
    }

    public List<TransportSegment> getTransportSolution2() {
        return transportSolution2;
    }

    public void setTransportSolution2(List<TransportSegment> transportSolution2) {
        this.transportSolution2 = transportSolution2;
    }
}
