package com.travlendar.travlendarServer.model;

import com.travlendar.travlendarServer.logic.CalculatorCore;
import com.travlendar.travlendarServer.logic.CheapestCore;
import com.travlendar.travlendarServer.logic.EcologistCore;
import com.travlendar.travlendarServer.logic.FastestCore;

public enum Policy {
    GREEN {
        @Override
        public CalculatorCore getCore() {
            return new EcologistCore();
        }
    }, CHEAP {
        @Override
        public CalculatorCore getCore() {
            return new CheapestCore();
        }
    }, FAST {
        @Override
        public CalculatorCore getCore() {
            return new FastestCore();
        }
    };

    public abstract CalculatorCore getCore();
}
