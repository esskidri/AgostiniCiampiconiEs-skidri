package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.GoogleAPIHandler;
import com.travlendar.travlendarServer.logic.exceptions.MeanNotAvailableException;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GoogleResponseMappedObjectTest extends GoogleResponseMappedObject {


    public GoogleResponseMappedObject createDefault(String origin, String destination, String meanOfTransport,String arrivalTime  ){
        GoogleResponseMappedObject googleResponseMappedObject = GoogleAPIHandler.askGoogle(origin, destination,meanOfTransport, arrivalTime);
        return  googleResponseMappedObject;
    }

    @Test
    public void checkCompleteness() {
        GoogleResponseMappedObject grmo = createDefault("45.48588769999999,9.204282700000022", "45.4785547,9.235430700000052", "driving", "1513162800000");


        try {
            grmo.checkCompleteness("driving");
            assert(true);
        } catch (MeanNotAvailableException e) {
            assert(false);
        }

        //BECAUSE TRANSIT NOW DOESN'T WORK

        grmo = createDefault("45.48588769999999,9.204282700000022", "45.4785547,9.235430700000052", "transit", "1513162800000");

        assert(grmo.getStatus().equals("ZERO_RESULTS"));

        try {
            grmo.checkCompleteness("transit");
            assert(false);
        } catch (MeanNotAvailableException e) {
            assert(true);
        }

        grmo = createDefault("Rome", "Auckland", "driving", "1513162800000");

        assert(grmo.getStatus().equals("ZERO_RESULTS"));

        try {
            grmo.checkCompleteness("");
            assert(false);
        } catch (MeanNotAvailableException e) {
            assert(true);
        }

        grmo = createDefault("45.48588769999999,9.204282700000022", "45.4785547,9.235430700000052", "driving", "1513162800000");
        int i = grmo.routes.get(0).getLegs().get(0).getSteps().size();

        i = i /2;

        while(i < grmo.routes.get(0).getLegs().get(0).getSteps().size()) {
            grmo.routes.get(0).getLegs().get(0).getSteps().get(i).setTravel_mode("walking");
            i++;
        }

        try {
            grmo.checkCompleteness("driving");
            assert(true);
            assert(grmo.isPartialSolution());
            assert(grmo.routes.get(0).getLegs().get(0).getSteps().size() == i/2);
            for(Step step: grmo.routes.get(0).getLegs().get(0).getSteps())
                assert(step.getTravel_mode().equals("DRIVING"));
        } catch (MeanNotAvailableException e) {
            assert(false);
        }
    }

}