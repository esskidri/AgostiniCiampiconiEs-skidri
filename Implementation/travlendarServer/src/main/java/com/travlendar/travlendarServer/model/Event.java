package com.travlendar.travlendarServer.model;

import java.sql.Timestamp;

public class Event implements Comparable<Event> {
    private long id;
    private Timestamp startDate;
    private Timestamp endDate;
    private float posX;
    private float posY;
    private boolean endEvent;



    public long getId() {
        return id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
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
