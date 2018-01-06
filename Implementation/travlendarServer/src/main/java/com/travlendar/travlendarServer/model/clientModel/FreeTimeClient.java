package com.travlendar.travlendarServer.model.clientModel;

import java.io.Serializable;
import java.sql.Timestamp;

public class FreeTimeClient implements Serializable{
    private Long id;
    private Timestamp startDate;
    private Timestamp endDate;
    private long duration;

    public FreeTimeClient(Long id, Timestamp startDate, Timestamp endDate, long duration) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
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
