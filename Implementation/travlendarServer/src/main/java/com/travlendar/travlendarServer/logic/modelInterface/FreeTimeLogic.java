package com.travlendar.travlendarServer.logic.modelInterface;

import java.sql.Timestamp;

/**
 * Created by Lorenzo on 06/01/2018.
 */
public interface FreeTimeLogic {
    Timestamp getStartDate();
    Timestamp getEndDate();
    long getDuration();
    boolean isSatisfied();
    void setSatisfied(boolean satisfied);
    Timestamp getSpendingStartDate();
    void setSpendingStartDate(Timestamp spendingStartDate);
    Timestamp getSpendingEndDate();
    void setSpendingEndDate(Timestamp spendingEndDate);
}
