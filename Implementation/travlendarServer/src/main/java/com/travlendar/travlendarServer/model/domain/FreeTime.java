package com.travlendar.travlendarServer.model.domain;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "free_time")
public class FreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name="duration")
    private int duration;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    public FreeTime(){}

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUser(User user) {
        this.user = user;
    }
}