package com.travlendar.travlendarServer.model;

import com.travlendar.travlendarServer.model.domain.Event;
import com.travlendar.travlendarServer.model.domain.Green;
import com.travlendar.travlendarServer.model.enumModel.EnumGreenLevel;

public enum MeanType {

    BIKE {
        @Override
        public String toHttpsFormat() {
            return "bicycling";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.HIGH;
        }
    },
    MOTORBIKE {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.LOW;
        }
    },
    CAR {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.LOW;
        }
    },
    ELECTRIC_CAR {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.HIGH;
        }
    },
    ELECTRIC_MOTORBIKE {
        @Override
        public String toHttpsFormat() {
            return "driving";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.HIGH;
        }
    },
    BUS {
        @Override
        public String toHttpsFormat() {
            return "transit";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.MEDIUM;
        }
    },WALKING {
        @Override
        public String toHttpsFormat() {
            return "walking";
        }

        @Override
        public EnumGreenLevel getGreenEnum() {
            return EnumGreenLevel.HIGH;
        }
    };

    /***
     *
     * Interface for Google HTTPS request
     *
     * @return
     */
    public abstract String toHttpsFormat();

    public abstract EnumGreenLevel getGreenEnum();
}
