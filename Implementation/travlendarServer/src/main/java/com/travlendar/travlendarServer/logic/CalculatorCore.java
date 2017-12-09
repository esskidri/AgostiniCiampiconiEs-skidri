package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.model.domain.UserPreferences;

import java.util.List;

public interface CalculatorCore {
    List<MeanOfTransportLogic> getMeanOfTransports(UserPreferences userPreferences);
}
