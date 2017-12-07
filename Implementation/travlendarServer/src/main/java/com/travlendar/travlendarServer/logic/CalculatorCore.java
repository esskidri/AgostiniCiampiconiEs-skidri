package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.model.MeanOfTransport;

import java.util.List;

public interface CalculatorCore {
    List<MeanOfTransport> getMeanOfTransports(UserPreferences userPreferences);
}
