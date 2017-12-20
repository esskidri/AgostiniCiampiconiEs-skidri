package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.model.enumModel.EnumGreenLevel;
import com.travlendar.travlendarServer.model.enumModel.MeanType;

public interface MeanOfTransportLogic {

    MeanType getTypeOfTransport();
    EnumGreenLevel getGreenLevel();
    boolean isPrivate();
}
