package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @NotNull
    @Column(name = "start_date")
    private Timestamp startDate;
    @NotNull
    @Column(name = "end_date")
    private Timestamp EndDate;
    @Column(name = "position_x")
    private float positionX;
    @Column(name = "position_y")
    private float positionY;
    @Column(name = "decription")
    private String description;
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "event1", targetEntity = TransportSolution.class)
    private TransportSolution transportSolution1;
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "event2", targetEntity = TransportSolution.class)
    private TransportSolution transportSolution2;
    @Column(name = "end_event")
    private int endEvent;
    @Column(name = "name")
    private String name;


    public Event(){}

    public Event(long userId, Timestamp startDate, Timestamp endDate, float positionX, float positionY, String description, TransportSolution transportSolution1, TransportSolution transportSolution2, int endEvent, String name) {
        this.userId = userId;
        this.startDate = startDate;
        EndDate = endDate;
        this.positionX = positionX;
        this.positionY = positionY;
        this.description = description;
        this.transportSolution1 = transportSolution1;
        this.transportSolution2 = transportSolution2;
        this.endEvent = endEvent;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return EndDate;
    }

    public void setEndDate(Timestamp endDate) {
        EndDate = endDate;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(int endEvent) {
        this.endEvent = endEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
