package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;

import java.util.List;

public class EcologistCore implements CalculatorCore {

    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic) {
        List<MeanOfTransportLogic> meansOfTransport = userLogic.getMeanPreferences();

        return null;
    }

    private boolean comapareMeanOfTransport(MeanOfTransportLogic meanOfTransport1, MeanOfTransportLogic meanOfTransport2){
        return true;
    }
}
