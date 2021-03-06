package com.travlendar.travlendarServer.logic.modelInterface;

import java.sql.Timestamp;
import java.util.List;

public interface TransportSolutionLogic {
    void setTransportSegmentsByLogic(List<TransportSegmentLogic> transportSegmentsLogic);
    void setStartEvent(EventLogic event);
    void setEndEvent(EventLogic event);
    EventLogic getStartEvent();
    EventLogic getEndEvent();
    Timestamp getDepartureTime();
    Timestamp getArrivalTime();
    List<MeanOfTransportLogic> getPrivateMeansUsed();
    boolean isEmpty();
    List<TransportSegmentLogic> getTransportSegmentsLogic();
}
