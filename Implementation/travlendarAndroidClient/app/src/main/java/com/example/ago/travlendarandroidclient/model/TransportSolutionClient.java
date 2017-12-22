package com.example.ago.travlendarandroidclient.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ago on 16/12/2017.
 */

public class TransportSolutionClient implements StepperTransportSolution  {


    @Override
    public List<StepperTransportSegment> getTransportSegment() {
        List<StepperTransportSegment> transportSegments = new ArrayList<>();
        //event a:
        TransportSegmentClient t;
        //evento di partenza
        transportSegments.add(new TransportSegmentClient(0,"Meeting in Buenos Aires",
                "Corso Buenos Aires","piazza Duomo","0.5","0","bike",0));
        //primo step
        transportSegments.add(new TransportSegmentClient(1,"Step ",
                "Piazza Duomo","Clericetti","3","0","bike",10));
        transportSegments.add(new TransportSegmentClient(2,"Step ",
                "Clericetti","Settala","0.3","0","bike",3));
        transportSegments.add(new TransportSegmentClient(3,"Step",
                "Settala","Leonardo","1.2","0","bike",6));
        //Evento di fine
        transportSegments.add(new TransportSegmentClient(3,"Polimi",
                "Leonardo","Leonardo","4","0","bike",3));



        return transportSegments;
    }
}
