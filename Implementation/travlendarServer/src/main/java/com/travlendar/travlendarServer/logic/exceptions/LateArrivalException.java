package com.travlendar.travlendarServer.logic.exceptions;

/**
 * Created by Lorenzo on 20/12/2017.
 */
public class LateArrivalException extends Exception {
    public LateArrivalException() {
    }

    public LateArrivalException(String message) {
        super(message);
    }
}
