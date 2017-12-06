package com.travlendar.travlendarServer.model;

import java.util.Date;

public class Event implements Comparable<Event> {
    private int id;
    private Date startTime;
    private Date endTime;
    private float position;
    private boolean endEvent;

    public int getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public float getPosition() {
        return position;
    }

    public boolean getEndEvent() {
        return endEvent;
    }

    public boolean overlapping(Event e){
        if(this.compareTo(e) < 0 && e.startTime.compareTo(this.endTime) < 0)
            return true;
        return false;
    }

    @Override
    public int compareTo(Event e) {
        if(this.startTime.compareTo(e.startTime)!= 0)
            return this.startTime.compareTo(e.startTime);
        else
            return this.endTime.compareTo(endTime);
    }
}
