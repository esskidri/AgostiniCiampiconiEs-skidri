package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.model.domain.Event;

import java.sql.Timestamp;

public interface EventLogic extends Comparable<EventLogic> {
    Coordinates getCoordinates();
    boolean isEndEvent();
    Timestamp getStartDate();
    Timestamp getEndDate();
    boolean overlapping(EventLogic e);
}
