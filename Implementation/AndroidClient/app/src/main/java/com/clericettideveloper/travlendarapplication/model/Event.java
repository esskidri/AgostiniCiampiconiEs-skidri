package com.clericettideveloper.travlendarapplication.model;

/**
 * Created by ago on 06/11/2017.
 */

public class Event extends TimeOccupation {
    private String startingAddress;
    private String endingAddress;

    @Override
    public boolean isOverlapping(TimeOccupation timeOccupation) {
        //TODO

        return false;
    }
}
