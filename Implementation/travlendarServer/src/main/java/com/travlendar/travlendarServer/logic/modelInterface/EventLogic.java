package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;

import java.sql.Timestamp;

public interface EventLogic extends Comparable<EventLogic> {
    Coordinates getCoordinates();
    Coordinates getCurrentHome();
    boolean isEndEvent();
    Timestamp getStartDate();
    Timestamp getEndDate();
    boolean overlapping(EventLogic e);
    boolean atHome();
    void setUser(UserLogic user);
    void setAtHome(boolean atHome);

}
