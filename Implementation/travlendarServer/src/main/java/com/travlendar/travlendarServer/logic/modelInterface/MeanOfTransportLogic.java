package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.model.MeanType;

public interface MeanOfTransportLogic {

    public MeanType getTypeOfTransport();

    boolean isPrivate();
}
