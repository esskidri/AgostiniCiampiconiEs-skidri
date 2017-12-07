package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "transport_segment")
public class TransportSegment implements Serializable{


    @Column(name = "num_order",insertable = false, updatable = false)
    private long numOrder;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "transport_solution_event_1", referencedColumnName = "event_id_1",insertable = false, updatable = false),
            @JoinColumn(name = "transport_solution_event_2",  referencedColumnName = "event_id_2",insertable = false, updatable = false)
    })
    private TransportSolution transportSolution;

    @EmbeddedId
    private TransportSegmentId transportSegmentId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="private_transport_id")
    private PrivateTransport privateTransport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="public_transport_id")
    private PublicTransport publicTransport;

    @Column(name="position_a_x")
    private Float positionAX;

    @Column(name="position_a_y")
    private Float positionAY;

    @Column(name="position_b_x")
    private Float positionBX;

    @Column(name="position_b_y")
    private Float positionBY;

    public TransportSegment(){

    }

    public TransportSegment(TransportSolution transportSolution, PrivateTransport privateTransport,
                            PublicTransport publicTransport, Float positionAX, Float positionAY,
                            Float positionBX, Float positionBY) {
        this.transportSolution = transportSolution;
        this.privateTransport = privateTransport;
        this.publicTransport = publicTransport;
        this.positionAX = positionAX;
        this.positionAY = positionAY;
        this.positionBX = positionBX;
        this.positionBY = positionBY;
    }

    public long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(long numOrder) {
        this.numOrder = numOrder;
    }

    public TransportSolution getTransportSolution() {
        return transportSolution;
    }

    public void setTransportSolution(TransportSolution transportSolution) {
        this.transportSolution = transportSolution;
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

    public Float getPositionAX() {
        return positionAX;
    }

    public void setPositionAX(Float positionAX) {
        this.positionAX = positionAX;
    }

    public Float getPositionAY() {
        return positionAY;
    }

    public void setPositionAY(Float positionAY) {
        this.positionAY = positionAY;
    }

    public Float getPositionBX() {
        return positionBX;
    }

    public void setPositionBX(Float positionBX) {
        this.positionBX = positionBX;
    }

    public Float getPositionBY() {
        return positionBY;
    }

    public void setPositionBY(Float positionBY) {
        this.positionBY = positionBY;
    }
}
