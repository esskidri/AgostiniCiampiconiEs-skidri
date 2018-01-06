package com.travlendar.travlendarServer.model.domain;


import com.travlendar.travlendarServer.logic.modelInterface.FreeTimeLogic;
import com.travlendar.travlendarServer.model.clientModel.FreeTimeClient;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "free_time")
public class FreeTime extends AbstractEntity implements FreeTimeLogic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name="duration")
    private long duration;

    @Column(name="is_satisfied")
    private boolean isSatisfied;

    @Column(name = "spending_start_date")
    private Timestamp spendingStartDate;

    @Column(name="spending_end_date")
    private Timestamp spendingEndDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    public FreeTime(){}

    public FreeTime(Timestamp startDate, Timestamp endDate, int duration, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public Timestamp getSpendingStartDate() {
        return spendingStartDate;
    }

    public void setSpendingStartDate(Timestamp spendingStartDate) {
        this.spendingStartDate = spendingStartDate;
    }

    public Timestamp getSpendingEndDate() {
        return spendingEndDate;
    }

    public void setSpendingEndDate(Timestamp spendingEndDate) {
        this.spendingEndDate = spendingEndDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FreeTimeClient getFreeTimeClient(){
        FreeTimeClient freeTimeClient = new FreeTimeClient(id,startDate,endDate,duration);
        return freeTimeClient;
    }
}