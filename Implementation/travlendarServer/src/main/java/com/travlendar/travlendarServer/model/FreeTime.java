package com.travlendar.travlendarServer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "free_time")
public class FreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Timestamp EndDate;
    @NotNull
    private int duration;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private long userId;

    public FreeTime(Timestamp startDate, Timestamp endDate, int duration, long userId) {
        this.startDate = startDate;
        EndDate = endDate;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}