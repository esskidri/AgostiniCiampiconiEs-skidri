package com.clericettideveloper.travlendarapplication.model;

/**
 * Created by Lorenzo on 11/6/2017.
 */

public class FreeTime extends TimeOccupation {
    private Long duration;


    @Override
    public boolean isOverlapping(TimeOccupation timeOccupation) {
        //TODO

        return false;
    }
}
