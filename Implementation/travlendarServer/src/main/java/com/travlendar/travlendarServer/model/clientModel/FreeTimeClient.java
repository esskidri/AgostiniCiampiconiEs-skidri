package com.travlendar.travlendarServer.model.clientModel;


import java.io.Serializable;
import java.sql.Timestamp;

public class FreeTimeClient implements Serializable{
    private Long id;
    private Timestamp startDate;
    private Timestamp endDate;
    private long duration;
    private boolean isSatisfied;
    private Timestamp spendingStartDate;
    private Timestamp spendingEndDate;
    private long userId;

    public FreeTimeClient(Long id, Timestamp startDate, Timestamp endDate, long duration) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    public FreeTimeClient(Timestamp startDate, Timestamp endDate, long duration, boolean isSatisfied, Timestamp spendingStartDate, Timestamp spendingEndDate, long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.isSatisfied = isSatisfied;
        this.spendingStartDate = spendingStartDate;
        this.spendingEndDate = spendingEndDate;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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


}
