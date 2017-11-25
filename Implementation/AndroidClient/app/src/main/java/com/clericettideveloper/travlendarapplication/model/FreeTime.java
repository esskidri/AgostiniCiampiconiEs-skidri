package com.clericettideveloper.travlendarapplication.model;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by Lorenzo on 11/6/2017.
 */

@Entity
public class FreeTime extends TimeOccupation {
    private Long duration;


    @Override
    public boolean isOverlapping(TimeOccupation timeOccupation) {
        //TODO

        return false;
    }
}
