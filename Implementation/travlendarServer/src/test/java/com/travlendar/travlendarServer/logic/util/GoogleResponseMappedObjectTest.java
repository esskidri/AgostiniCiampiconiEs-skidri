package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.GoogleAPIHandler;
import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.*;
import org.junit.Test;

import java.sql.Timestamp;

public class GoogleResponseMappedObjectTest extends GoogleResponseMappedObject {


   public GoogleResponseMappedObject createDefault(Coordinates origin, Coordinates destination, String meanOfTransport, Timestamp arrivalTime){
       return GoogleAPIHandler.askGoogle(origin, destination, meanOfTransport, TimeRequest.ARRIVAL.toHttpsFormat(), arrivalTime);
    }



    @Test
    public void checkCompleteness() {
       Timestamp arrivalTime = new Timestamp(((long)1513880400) * 1000);
       Coordinates origin = new Coordinates();
       Coordinates destination = new Coordinates();
       origin.setLat((float) 45.485887);
       origin.setLng((float) 9.20428);
       destination.setLat((float) 45.4785547);
       destination.setLng((float) 9.23543);
        GoogleResponseMappedObject grmo = createDefault(origin, destination, "driving", arrivalTime);


        try {
            grmo.checkCompleteness("driving", TimeRequest.ARRIVAL, arrivalTime);
            assert(true);
        } catch (MeanNotAvailableException e) {
            assert(false);
        }

        //BECAUSE TRANSIT NOW DOESN'T WORK

        grmo = createDefault(origin, destination, "transit", arrivalTime);

        assert(grmo.getStatus().equals("OK"));

        try {
            grmo.checkCompleteness("transit", TimeRequest.ARRIVAL, arrivalTime);
            assert(true);
        } catch (MeanNotAvailableException e) {
            assert(false);
        }


        grmo = createDefault(origin, destination, "driving", arrivalTime);
        int i = grmo.getSteps().size();

        i = i /2;

        while(i < grmo.getSteps().size()) {
            grmo.getSteps().get(i).setTravel_mode("walking");
            i++;
        }

        try {
            grmo.checkCompleteness("driving", TimeRequest.ARRIVAL, arrivalTime);
            assert(true);
            assert(grmo.isPartialSolution());
            assert(grmo.getSteps().size() == i/2);
            for(Step step: grmo.getSteps())
                assert(step.getTravel_mode().equals("DRIVING"));
        } catch (MeanNotAvailableException e) {
            assert(false);
        }
    }

}