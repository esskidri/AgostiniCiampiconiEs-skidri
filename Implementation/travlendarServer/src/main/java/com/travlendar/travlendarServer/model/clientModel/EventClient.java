package com.travlendar.travlendarServer.model.clientModel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class EventClient implements Serializable{

    private long id;
    private Timestamp startDate;
    private Timestamp endDate;
    private float posX;
    private float posY;
    private String description;
    private String name;
    private boolean endEvent;
    private List<TransportSolutionClient> transportSolutionClientListA;
    private List<TransportSolutionClient> transportSolutionClientListB;

    public EventClient(long id, Timestamp startDate, Timestamp endDate, float posX, float posY, String description, String name, boolean endEvent) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
    }

    public EventClient(Timestamp startDate, Timestamp endDate, float posX, float posY, String description, String name, boolean endEvent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
    }

    public EventClient(long id, Timestamp startDate, Timestamp endDate, float posX, float posY, String description,
                       String name, boolean endEvent, List<TransportSolutionClient> transportSolutionClientListA,
                       List<TransportSolutionClient> transportSolutionClientListB) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
        this.transportSolutionClientListA = transportSolutionClientListA;
        this.transportSolutionClientListB =transportSolutionClientListB;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEndEvent() {
        return endEvent;
    }

    public void setEndEvent(boolean endEvent) {
        this.endEvent = endEvent;
    }

    public List<TransportSolutionClient> getTransportSolutionClientListA() {
        return transportSolutionClientListA;
    }

    public void setTransportSolutionClientListA(List<TransportSolutionClient> transportSolutionClientListA) {
        this.transportSolutionClientListA = transportSolutionClientListA;
    }

    public List<TransportSolutionClient> getTransportSolutionClientListB() {
        return transportSolutionClientListB;
    }

    public void setTransportSolutionClientListB(List<TransportSolutionClient> transportSolutionClientListB) {
        this.transportSolutionClientListB = transportSolutionClientListB;
    }
}

