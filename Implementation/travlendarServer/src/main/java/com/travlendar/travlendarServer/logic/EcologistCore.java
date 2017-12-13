package com.travlendar.travlendarServer.logic;

import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;

import java.util.List;

public class EcologistCore implements CalculatorCore {

    @Override
    public List<MeanOfTransportLogic> getMeanOfTransports(UserLogic userLogic) {
        List<MeanOfTransportLogic> meansOfTransport = userLogic.getMeanPreferences();

        orderMeanByGreen(meansOfTransport, 0);

        return meansOfTransport;
    }

    private boolean compareGreenMeanOfTransport(MeanOfTransportLogic meanOfTransport1, MeanOfTransportLogic meanOfTransport2){
        return meanOfTransport1.getGreenLevel().compareTo(meanOfTransport2.getGreenLevel()) >= 0;
    }

    private void orderMeanByGreen(List<MeanOfTransportLogic> meansOfTransport, int index){
        MeanOfTransportLogic best = meansOfTransport.get(index);

        for(MeanOfTransportLogic meanOfTransport: meansOfTransport.subList(index +1, meansOfTransport.size())){
            if(!compareGreenMeanOfTransport(best, meanOfTransport))
                best = meanOfTransport;
        }

        if(meansOfTransport.indexOf(best) != index) {
            meansOfTransport.remove(best);
            meansOfTransport.add(index, best);
        }

        if(index < meansOfTransport.size() -1)
            orderMeanByGreen(meansOfTransport, index +1);
    }
}
