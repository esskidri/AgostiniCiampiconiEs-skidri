package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.model.domain.UserTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EcologistCoreTest {
    EcologistCore ecologistCore = new EcologistCore();

    @Test
    public void getMeanOfTransports() throws Exception {
        UserLogic userLogic = UserTest.createUser();
        List<MeanOfTransportLogic> userOrder = userLogic.getMeanPreferences();

        List<MeanOfTransportLogic> meanOfTransportLogics = ecologistCore.getMeanOfTransports(userLogic, null, null, null, null);

        int i = 0;
        for(MeanOfTransportLogic meanOfTransportLogic: meanOfTransportLogics){
            if(i < meanOfTransportLogics.size() -1) {
                assert(meanOfTransportLogic.getGreenLevel().compareTo(meanOfTransportLogics.get(i + 1).getGreenLevel()) >= 0);
                assert((meanOfTransportLogic.getGreenLevel().compareTo(meanOfTransportLogics.get(i + 1).getGreenLevel()) != 0) || (userOrder.indexOf(meanOfTransportLogic) < userOrder.indexOf(meanOfTransportLogics.get(i + 1))));
            }
            i++;
        }
    }

}