package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.TypeOfTransport;

public interface MeanOfTransportLogic {

    public TypeOfTransport getTypeOfTransport();

    boolean isPrivate();
}
