package com.travlendar.travlendarServer.model;

import javax.persistence.*;

@Entity
@Table(name = "transport_segment")
public class TransportSegment {


    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_solution")
    private long transportSolutionEventId1;
    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_solution")
    private long transportSolutionEventId2;
    @Id
    private long numOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_transport")
    private long privateTransportId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "public_transport")
    private long publicTransportId;
    @Column(unique=true)
    private float positionA;
    @Column(unique=true)
    private float positionB;

    public TransportSegment(long transportSolutionEventId1, long transportSolutionEventId2, long numOrder, long privateTransportId, long publicTransportId, float positionA, float positionB) {
        this.transportSolutionEventId1 = transportSolutionEventId1;
        this.transportSolutionEventId2 = transportSolutionEventId2;
        this.numOrder = numOrder;
        this.privateTransportId = privateTransportId;
        this.publicTransportId = publicTransportId;
        this.positionA = positionA;
        this.positionB = positionB;
    }

    public long getTransportSolutionEventId1() {
        return transportSolutionEventId1;
    }

    public void setTransportSolutionEventId1(long transportSolutionEventId1) {
        this.transportSolutionEventId1 = transportSolutionEventId1;
    }

    public long getTransportSolutionEventId2() {
        return transportSolutionEventId2;
    }

    public void setTransportSolutionEventId2(long transportSolutionEventId2) {
        this.transportSolutionEventId2 = transportSolutionEventId2;
    }

    public long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(long numOrder) {
        this.numOrder = numOrder;
    }

    public long getPrivateTransportId() {
        return privateTransportId;
    }

    public void setPrivateTransportId(long privateTransportId) {
        this.privateTransportId = privateTransportId;
    }

    public long getPublicTransportId() {
        return publicTransportId;
    }

    public void setPublicTransportId(long publicTransportId) {
        this.publicTransportId = publicTransportId;
    }

    public float getPositionA() {
        return positionA;
    }

    public void setPositionA(float positionA) {
        this.positionA = positionA;
    }

    public float getPositionB() {
        return positionB;
    }

    public void setPositionB(float positionB) {
        this.positionB = positionB;
    }
}
