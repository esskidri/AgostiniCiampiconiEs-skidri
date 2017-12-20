package com.travlendar.travlendarServer.model.domain;

import com.travlendar.travlendarServer.logic.modelInterface.TransportSegmentLogic;
import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "transport_segment")
public class TransportSegment extends AbstractEntity implements Serializable,TransportSegmentLogic {
    @Column(name = "num_order",insertable = false, updatable = false)
    private Long numOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "transport_solution_event_id_1", referencedColumnName = "event_id_1",insertable = false, updatable = false),
            @JoinColumn(name = "transport_solution_event_id_2",  referencedColumnName = "event_id_2",insertable = false, updatable = false)
    })
    private TransportSolution transportSolution;

    @EmbeddedId
    private TransportSegmentId transportSegmentId;

    public TransportSegmentId getTransportSegmentId() {
        return transportSegmentId;
    }

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

    //distance in meters
    @Column(name="distance")
    private int distance;

    @Column(name="description")
    private String description;

    //duration in seconds
    @Column(name="duration")
    private int duration;

    //TODO add to DB
    private Timestamp departureTime;

    private Timestamp arrivalTime;

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

    public Long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(Long numOrder) {
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

    public void setTransportSegmentId(TransportSegmentId transportSegmentId) {
        this.transportSegmentId = transportSegmentId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /***
     *  Logic Interface Implementation
     ***/

    @Override
    public void setMeanOfTransport(MeanOfTransportLogic meanOfTransport) {
        if(meanOfTransport.isPrivate())
            privateTransport = (PrivateTransport) meanOfTransport;
        else
            publicTransport = (PublicTransport) meanOfTransport;
    }

    @Override
    public void setOrigin(Coordinates coordinates) {
        positionAX = coordinates.getLat();
        positionAY = coordinates.getLng();
    }

    @Override
    public void setDestination(Coordinates coordinates) {
        positionBX = coordinates.getLat();
        positionBY = coordinates.getLng();
    }

    @Override
    public Coordinates getOrigin() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(positionAX);
        coordinates.setLng(positionAY);
        return coordinates;
    }

    @Override
    public Coordinates getDestination() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(positionBX);
        coordinates.setLng(positionBX);
        return coordinates;
    }

    @Override
    public boolean isAdiacent(Coordinates coordinates) {
        return coordinates.getLat() == positionBX && coordinates.getLng() == positionBY;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
