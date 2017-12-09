package com.travlendar.travlendarServer.logic;

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
