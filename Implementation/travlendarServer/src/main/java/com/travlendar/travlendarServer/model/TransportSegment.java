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
    private float positionAx;
    private float positionBx;
    private float positionAy;
    private float positionBy;


    public TransportSegment(long transportSolutionEventId1, long transportSolutionEventId2, long numOrder, long privateTransportId, long publicTransportId, float positionAx, float positionBx, float positionAy, float positionBy) {
        this.transportSolutionEventId1 = transportSolutionEventId1;
        this.transportSolutionEventId2 = transportSolutionEventId2;
        this.numOrder = numOrder;
        this.privateTransportId = privateTransportId;
        this.publicTransportId = publicTransportId;
        this.positionAx = positionAx;
        this.positionBx = positionBx;
        this.positionAy = positionAy;
        this.positionBy = positionBy;
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

    public float getPositionAx() {
        return positionAx;
    }

    public void setPositionAx(float positionAx) {
        this.positionAx = positionAx;
    }

    public float getPositionBx() {
        return positionBx;
    }

    public void setPositionBx(float positionBx) {
        this.positionBx = positionBx;
    }

    public float getPositionAy() {
        return positionAy;
    }

    public void setPositionAy(float positionAy) {
        this.positionAy = positionAy;
    }

    public float getPositionBy() {
        return positionBy;
    }

    public void setPositionBy(float positionBy) {
        this.positionBy = positionBy;
    }
}
