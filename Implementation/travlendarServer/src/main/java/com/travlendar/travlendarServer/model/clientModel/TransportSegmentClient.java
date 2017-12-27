package com.travlendar.travlendarServer.model.clientModel;


import java.sql.Timestamp;

public class TransportSegmentClient {

   private Long numOrder;

   private PrivateTransportClient privateTransportClient;

   private PublicTransportClient publicTransportClient;

   private Float positionAX;

   private Float positionAY;

   private Float positionBX;

   private Float positionBY;

   private long distance;

   private String description;

   private long duration;


    private Timestamp departureTime;

    private Timestamp arrivalTime;

    public Long getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(Long numOrder) {
        this.numOrder = numOrder;
    }

    public PrivateTransportClient getPrivateTransportClient() {
        return privateTransportClient;
    }

    public void setPrivateTransportClient(PrivateTransportClient privateTransportClient) {
        this.privateTransportClient = privateTransportClient;
    }

    public PublicTransportClient getPublicTransportClient() {
        return publicTransportClient;
    }

    public void setPublicTransportClient(PublicTransportClient publicTransportClient) {
        this.publicTransportClient = publicTransportClient;
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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public TransportSegmentClient(Long numOrder, PrivateTransportClient privateTransportClient, PublicTransportClient publicTransportClient, Float positionAX, Float positionAY, Float positionBX, Float positionBY, long distance, String description, long duration, Timestamp departureTime, Timestamp arrivalTime) {
        this.numOrder = numOrder;
        this.privateTransportClient = privateTransportClient;
        this.publicTransportClient = publicTransportClient;
        this.positionAX = positionAX;
        this.positionAY = positionAY;
        this.positionBX = positionBX;
        this.positionBY = positionBY;
        this.distance = distance;
        this.description = description;
        this.duration = duration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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
