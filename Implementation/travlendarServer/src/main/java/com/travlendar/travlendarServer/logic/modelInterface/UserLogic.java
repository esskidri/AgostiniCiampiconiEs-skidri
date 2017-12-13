package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.CalculatorCore;
import com.travlendar.travlendarServer.model.Policy;

import java.util.List;

public interface UserLogic {
    List<MeanOfTransportLogic> getMeanPreferences();

    Policy getPolicy();
}
