package com.travlendar.travlendarServer.logic.util;

/**
 * Created by Lorenzo on 20/12/2017.
 */
public enum TimeRequest {
    ARRIVAL {
        @Override
        public String toHttpsFormat() {
            return "arrival_time";
        }
    }, DEPARTURE {
        @Override
        public String toHttpsFormat() {
            return "departure_time";
        }
    };

    public abstract String toHttpsFormat();
}
