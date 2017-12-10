package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;

import java.util.List;

public interface CalculatorCore {
    List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic);
}
