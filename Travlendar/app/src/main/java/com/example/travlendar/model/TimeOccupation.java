package com.example.travlendar.model;

import java.util.Date;

/**
 * Created by Lorenzo on 11/6/2017.
 */
public abstract class TimeOccupation {
    private String name;
    private Date startTime;
    private Date endTime;

    public boolean before(TimeOccupation timeOccupation){
        if(this.startTime.before(timeOccupation.startTime))
            return true;
        return false;
    }

    public static boolean isOverlapping(TimeOccupation timeOccupation1, TimeOccupation timeOccupation2){
        return timeOccupation1.isOverlapping(timeOccupation2) || timeOccupation2.isOverlapping(timeOccupation1);
    }

    public abstract boolean isOverlapping(TimeOccupation timeOccupation);
}
