package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.model.EnumGreenLevel;
import com.travlendar.travlendarServer.model.MeanType;

public interface MeanOfTransportLogic {

    MeanType getTypeOfTransport();
    EnumGreenLevel getGreenLevel();
    boolean isPrivate();
}
