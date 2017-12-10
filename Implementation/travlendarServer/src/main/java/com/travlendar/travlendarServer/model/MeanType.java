package com.travlendar.travlendarServer.model;

public enum MeanType {

    BIKE {
        @Override
        public String toHttpsFormat() {
            return "bicycling";
        }
    },
    MOTORBIKE {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }
    },
    CAR {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }
    },
    ELECTRIC_CAR {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }
    },
    ELECTRIC_MOTORBIKE {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }
    },
    BUS {
        @Override
        public String toHttpsFormat() {
            return "transit";
        }
    };

    /***
     *
     * Interface for Google HTTPS request
     *
     * @return
     */
    public abstract String toHttpsFormat();
}
