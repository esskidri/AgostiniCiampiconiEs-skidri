package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Timestamp EndDate;
    private float positionX;
    private float positionY;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private long userId;
    private boolean endEvent;
    private String name;

    public event(Timestamp startDate, Timestamp endDate, float positionX, float positionY, String description, long userId, boolean endEvent, String name) {
        this.startDate = startDate;
        EndDate = endDate;
        this.positionX = positionX;
        this.positionY = positionY;
        this.description = description;
        this.userId = userId;
        this.endEvent = endEvent;
        this.name = name;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isEndEvent() {
        return endEvent;
    }

    public void setEndEvent(boolean endEvent) {
        this.endEvent = endEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
