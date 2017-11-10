package com.clericettideveloper.travlendarapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorenzo on 11/6/2017.
 */

public class Calendar {
    private List<TimeOccupation> timeOccupations;

    public Calendar(List<TimeOccupation> timeOccupations) {
        this.timeOccupations = timeOccupations;
    }

    public void insertTimeOccupation(TimeOccupation newTimeOccupation){

        List<TimeOccupation> timeOccupationsCopy = new ArrayList<>();
        timeOccupationsCopy.addAll(timeOccupations);

        int i = 0;

        for(TimeOccupation timeOccupation: timeOccupationsCopy){
            if(newTimeOccupation.before(timeOccupation)) {
                timeOccupations.add(i, newTimeOccupation);
            }
            else i++;
        }
    }

    public void removeTimeOccupaton(int i){
        timeOccupations.remove(i);
    }
}
