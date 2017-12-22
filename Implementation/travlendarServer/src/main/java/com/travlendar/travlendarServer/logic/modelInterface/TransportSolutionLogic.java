package com.travlendar.travlendarServer.logic.modelInterface;

import java.sql.Timestamp;
import java.util.List;

public interface TransportSolutionLogic {
    void setTransportSegmentsByLogic(List<TransportSegmentLogic> transportSegmentsLogic);
    void setStartEvent(EventLogic event);
    void setEndEvent(EventLogic event);
    Timestamp getArrivalTime();
}
