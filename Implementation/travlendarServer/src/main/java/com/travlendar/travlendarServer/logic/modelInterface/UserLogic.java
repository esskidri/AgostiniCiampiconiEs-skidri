package com.travlendar.travlendarServer.logic.modelInterface;

import com.travlendar.travlendarServer.logic.util.googleJsonSubClass.Coordinates;
import com.travlendar.travlendarServer.model.enumModel.Policy;

import java.util.List;

public interface UserLogic {
    List<MeanOfTransportLogic> getMeanPreferences();
    Policy getPolicy();
    Coordinates getHomeCoordinates();
}
