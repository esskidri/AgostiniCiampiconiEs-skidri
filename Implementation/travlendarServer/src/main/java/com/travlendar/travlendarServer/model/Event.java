package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name = "pos_x")
    private float posX;

    @Column(name = "pos_y")
    private float posY;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "end_event")
    private  boolean endEvent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy="event1")
    private TransportSolution transportSolution1;
    @OneToOne(mappedBy="event2")
    private TransportSolution transportSolution2;


    public Event(Timestamp startDate, Timestamp endDate, float posX, float posY, String description, String name, boolean endEvent, User user, TransportSolution transportSolution1, TransportSolution transportSolution2) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.posX = posX;
        this.posY = posY;
        this.description = description;
        this.name = name;
        this.endEvent = endEvent;
        this.user = user;
        this.transportSolution1 = transportSolution1;
        this.transportSolution2 = transportSolution2;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
