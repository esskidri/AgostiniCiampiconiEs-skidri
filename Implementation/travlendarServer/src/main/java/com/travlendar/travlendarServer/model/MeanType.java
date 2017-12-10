package com.travlendar.travlendarServer.logic;

public enum TypeOfTransport {

    DRIVING {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }
    }, WALKING {
        @Override
        public String toHttpsFormat() {
            return "walking";
        }
    }, BICYCLING {
        @Override
        public String toHttpsFormat() {
            return "bicycling";
        }
    }, TRANSIT {
        @Override
        public String toHttpsFormat() {
            return "transit";
        }
    };

    public abstract String toHttpsFormat();
}
