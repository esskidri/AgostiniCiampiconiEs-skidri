package com.travlendar.travlendarServer.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event")
public class Event implements Serializable,Comparable<Event>{
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event1")
    private List<TransportSolution> transportSolutions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event2")
    private List<TransportSolution> transportSolutions2;

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

    public List<TransportSolution> getTransportSolutions() {
        return transportSolutions;
    }

    public void setTransportSolutions(List<TransportSolution> transportSolutions) {
        this.transportSolutions = transportSolutions;
    }

    public List<TransportSolution> getTransportSolutions2() {
        return transportSolutions2;
    }

    public void setTransportSolutions2(List<TransportSolution> transportSolutions2) {
        this.transportSolutions2 = transportSolutions2;
    }
    public boolean getEndEvent() {
        return endEvent;
    }

    public boolean overlapping(Event e){
        if(this.compareTo(e) < 0 && e.startDate.compareTo(this.endDate) < 0)
            return true;
        return false;
    }

    @Override
    public int compareTo(Event e) {
        if(this.startDate.compareTo(e.startDate)!= 0)
            return this.startDate.compareTo(e.startDate);
        else
            return this.endDate.compareTo(endDate);
    }
}
