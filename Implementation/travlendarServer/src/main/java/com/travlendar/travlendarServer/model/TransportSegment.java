package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transport_solution")
public class TransportSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num_order")
    private long numOrder;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="transport_solution_event_1")
    private TransportSolution transportSolution1;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="transport_solution_event_2")
    private TransportSolution transportSolution2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="private_transport_id")
    private PrivateTransport privateTransport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="public_transport_id")
    private PublicTransport publicTransport;

    public TransportSegment(TransportSolution transportSolution1, TransportSolution transportSolution2, PrivateTransport privateTransport, PublicTransport publicTransport) {
        this.transportSolution1 = transportSolution1;
        this.transportSolution2 = transportSolution2;
        this.privateTransport = privateTransport;
        this.publicTransport = publicTransport;
    }

    public long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(long numOrder) {
        this.numOrder = numOrder;
    }

    public TransportSolution getTransportSolution1() {
        return transportSolution1;
    }

    public void setTransportSolution1(TransportSolution transportSolution1) {
        this.transportSolution1 = transportSolution1;
    }

    public TransportSolution getTransportSolution2() {
        return transportSolution2;
    }

    public void setTransportSolution2(TransportSolution transportSolution2) {
        this.transportSolution2 = transportSolution2;
    }

    public PrivateTransport getPrivateTransport() {
        return privateTransport;
    }

    public void setPrivateTransport(PrivateTransport privateTransport) {
        this.privateTransport = privateTransport;
    }

    public PublicTransport getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }
}
