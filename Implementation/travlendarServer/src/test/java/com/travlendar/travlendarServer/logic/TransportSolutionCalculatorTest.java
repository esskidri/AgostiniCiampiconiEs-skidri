package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.util.GoogleResponseMappedObject;
import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class TransportSolutionCalculatorTest extends TransportSolutionCalculator {
    public TransportSolutionCalculatorTest(CalculatorCore calculatorCore) {
        super(calculatorCore, "arrival_time");
    }

    @Test
    public void calculateSolution() throws Exception {
        //TODO create UserLogic usable for the test
        TransportSolutionCalculator transportSolutionCalculator = new TransportSolutionCalculator(new EcologistCore(), "arrival_time");

        Class targeClass = TransportSolutionCalculator.class;
        Method methodOrderSegments = targeClass.getDeclaredMethod("orderSegments") ;
        Method methodInsertSegments = targeClass.getDeclaredMethod("insertTransportSegments", GoogleResponseMappedObject.class, MeanOfTransportLogic.class) ;
        Method methodCalculateSegments = targeClass.getDeclaredMethod("calculateSegment", Coordinates.class, Coordinates.class, Timestamp.class, Timestamp.class, List.class);
        Field field = targeClass.getDeclaredField("transportSegments");
        field.setAccessible(true);
        methodOrderSegments.setAccessible(true);
        methodInsertSegments.setAccessible(true);
        methodCalculateSegments.setAccessible(true);


    }



}