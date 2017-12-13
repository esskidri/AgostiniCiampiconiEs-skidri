package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;

import java.util.List;

public class CheapestCore implements CalculatorCore {
    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic) {
        //TODO
        return userLogic.getMeanPreferences();
    }
}
